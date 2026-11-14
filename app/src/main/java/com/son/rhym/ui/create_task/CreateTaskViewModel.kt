package com.son.rhym.ui.create_task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.son.rhym.data.local.mapper.toUiState
import com.son.rhym.domain.repository.TaskRepository
import com.son.rhym.ui.common.snackbar.SnackbarManager
import com.son.rhym.ui.common.snackbar.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val taskRepo: TaskRepository,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val taskId: Long = savedStateHandle["taskId"] ?: 0L
    val isEditing = taskId != 0L

    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> = _uiState.asStateFlow()

    init {
        if (isEditing) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                val task = taskRepo.getTaskById(taskId)
                if (task != null) {
                    _uiState.value = task.toUiState()
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onColorChange(c: Long) = _uiState.update { it.copy(color = c) }
    fun onIconPicked(icon: String) = _uiState.update { it.copy(icon = icon) }
    fun onDatePicked(d: LocalDate) = _uiState.update { it.copy(date = d) }

    fun onReminderToggle(enabled: Boolean) = _uiState.update {
        if (enabled) it.copy(isReminderEnabled = true)
        else it.copy(isReminderEnabled = false, reminderTimes = emptyList())
    }

    fun onReminderTimesChange(times: List<LocalTime>) =
        _uiState.update { it.copy(reminderTimes = times.distinct().sorted()) }

    fun save(onSuccess: () -> Unit = {}) {
        val state = _uiState.value
        if (state.name.isBlank()) {
            snackbarManager.show(UiText.Dynamic("Vui lòng nhập tên công việc!"))
            return
        }
        if (state.isSaving) return

        _uiState.update { it.copy(isSaving = true) }

        viewModelScope.launch {
            try {
                if (isEditing) {
                    taskRepo.updateTask(state.toDomain())
                    snackbarManager.show(UiText.Dynamic("Đã cập nhật công việc!"))
                } else {
                    taskRepo.insertTask(state.toDomain())
                    snackbarManager.show(UiText.Dynamic("Đã tạo công việc thành công!"))
                }
                _uiState.update { it.copy(isSaving = false) }
                onSuccess()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false) }
                snackbarManager.show(UiText.Dynamic("Lỗi khi lưu: ${e.localizedMessage ?: "Đã xảy ra lỗi"}"))
            }
        }
    }
}

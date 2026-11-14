package com.son.rhym.ui.create_habit

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.son.rhym.data.local.mapper.toUiState
import com.son.rhym.domain.model.GoalPeriod
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.domain.repository.HabitRepository
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
class CreateHabitViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val habitRepo: HabitRepository,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val habitId: Long = savedStateHandle["habitId"] ?: 0L
    val isEditing = habitId != 0L

    private val _uiState = MutableStateFlow(CreateHabitUiState())
    val uiState: StateFlow<CreateHabitUiState> = _uiState.asStateFlow()

    init {
        if (isEditing) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                val habit = habitRepo.getHabitById(habitId)
                if (habit != null) {
                    _uiState.value = habit.toUiState()
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onDescriptionChange(v: String) = _uiState.update { it.copy(description = v) }
    fun onColorChange(c: Long) = _uiState.update { it.copy(color = c) }
    fun onIconPicked(icon: String) = _uiState.update { it.copy(icon = icon) }

    fun showSnackbarMessage(message: String) {
        snackbarManager.show(UiText.Dynamic(message))
    }

    fun onEndDateToggle(enabled: Boolean) = _uiState.update {
        if (enabled) {
            it.copy(isEndDateEnabled = true, endDate = it.endDate ?: it.start.plusDays(14))
        } else {
            it.copy(isEndDateEnabled = false, endDate = null)
        }
    }

    fun save(onSuccess: () -> Unit = {}) {
        val state = _uiState.value
        if (state.name.isBlank()) {
            snackbarManager.show(UiText.Dynamic("Vui lòng nhập tên thói quen!"))
            return
        }
        if (state.isSaving) return

        _uiState.update { it.copy(isSaving = true) }

        viewModelScope.launch {
            try {
                Log.d("CreateHabitViewModel", "Saving habit (isEditing=$isEditing): ${state.toDomain()}")
                if (isEditing) {
                    habitRepo.updateHabit(state.toDomain())
                    snackbarManager.show(UiText.Dynamic("Đã cập nhật thói quen!"))
                } else {
                    habitRepo.insertHabit(state.toDomain())
                    snackbarManager.show(UiText.Dynamic("Đã tạo thói quen thành công!"))
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

    fun onGoalConfirmed(type: GoalType, amount: Int, unit: String, period: GoalPeriod) =
        _uiState.update {
            it.copy(
                goalType = type,
                goalAmount = amount.coerceAtLeast(1),
                goalUnit = unit.trim().ifBlank { it.goalUnit },
                goalPeriod = period
            )
        }

    fun onRepeatChange(config: RepeatConfig) =
        _uiState.update { it.copy(repeatConfig = config) }

    fun onReminderTimesChange(times: List<LocalTime>) =
        _uiState.update { it.copy(reminderTimes = times.distinct().sorted()) }

    fun onReminderToggle(enabled: Boolean) = _uiState.update {
        if (enabled) it.copy(isReminderEnabled = true)
        else it.copy(isReminderEnabled = false, reminderTimes = emptyList())
    }

    fun onStartDatePicked(d: LocalDate) = _uiState.update {
        it.copy(
            startDate = d,
            endDate = it.endDate?.let { e -> if (e.isBefore(d)) d else e }
        )
    }

    fun onEndDatePicked(d: LocalDate) = _uiState.update {
        it.copy(isEndDateEnabled = true, endDate = maxOf(d, it.start))
    }

}

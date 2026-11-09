package com.son.rhym.ui.create_habit

import androidx.lifecycle.ViewModel
import com.son.rhym.data.local.entity.HabitEntity
import com.son.rhym.domain.repository.HabitLogRepository
import com.son.rhym.domain.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CreateViewModel @Inject
constructor(
    private val habitRepo: HabitRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateHabitUiState())
    val uiState: StateFlow<CreateHabitUiState> = _uiState.asStateFlow()

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onDescriptionChange(v: String) = _uiState.update { it.copy(description = v) }
    fun onColorChange(c: Long) = _uiState.update { it.copy(color = c) }
    fun onIconPicked(icon: String) = _uiState.update { it.copy(icon = icon) }


    suspend fun save(createHabitUiState: CreateHabitUiState) {
//        if (habit.id == 0L) habitRepo.insertHabit(habit)
//        else {
//            habitDao.update(habit.copy(updatedAt = System.currentTimeMillis()))
//            logDao.updateTodaySnapshot(habit.id, habit.goalAmount, LocalDate.now())
//        }
    }
}
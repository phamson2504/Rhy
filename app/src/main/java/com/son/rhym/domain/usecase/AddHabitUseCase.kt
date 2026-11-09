package com.son.rhym.domain.usecase

import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.repository.HabitRepository
import jakarta.inject.Inject

class AddHabitUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit): Result<Unit> {
        if (habit.name.isBlank()) return Result.failure(IllegalArgumentException("Tên không được trống"))
        return runCatching { repository.insertHabit(habit) }
    }
}

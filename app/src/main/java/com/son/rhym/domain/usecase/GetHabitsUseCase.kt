package com.son.rhym.domain.usecase

import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.repository.HabitRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetHabitsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(): Flow<List<Habit>> = repository.getHabits()
}

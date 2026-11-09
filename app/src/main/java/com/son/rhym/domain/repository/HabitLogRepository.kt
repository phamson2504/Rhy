package com.son.rhym.domain.repository

import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.model.HabitLog
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HabitLogRepository {
    fun observeDay(date: LocalDate): Flow<List<HabitLog>>
    suspend fun setValue(habit: Habit, date: LocalDate, value: Int)
    suspend fun setNote(habitId: Long, date: LocalDate, note: String?)
}
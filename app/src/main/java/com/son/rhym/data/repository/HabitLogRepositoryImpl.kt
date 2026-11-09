package com.son.rhym.data.repository

import com.son.rhym.data.local.dao.HabitLogDao
import com.son.rhym.data.local.entity.HabitLogEntity
import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.model.HabitLog
import com.son.rhym.domain.model.toDomain
import com.son.rhym.domain.repository.HabitLogRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class HabitLogRepositoryImpl @Inject constructor(
    private val logDao: HabitLogDao
) : HabitLogRepository {
    override fun observeDay(date: LocalDate): Flow<List<HabitLog>> {
        return logDao.observeDay(date).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun setValue(
        habit: Habit,
        date: LocalDate,
        value: Int
    ) {
        val existing = logDao.getByDate(habit.id, date)
        when {
            value <= 0 -> existing?.let { logDao.delete(it) }
            existing == null -> logDao.insert(
                HabitLogEntity(
                    habitId = habit.id,
                    date = date,
                    value = value,
                    goalAmountSnapshot = habit.goalAmount
                )
            )

            else -> logDao.update(
                existing.copy(value = value, completedAt = System.currentTimeMillis())
            )
        }
    }

    override suspend fun setNote(
        habitId: Long,
        date: LocalDate,
        note: String?
    ) {
        val existing = logDao.getByDate(habitId, date) ?: return
        logDao.update(existing.copy(note = note))
    }
}
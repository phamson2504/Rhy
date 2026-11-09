package com.son.rhym.data.repository

import com.son.rhym.data.local.dao.HabitDao
import com.son.rhym.data.local.mapper.toDomain
import com.son.rhym.data.local.mapper.toEntity
import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.repository.HabitRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao,
) : HabitRepository {

    override fun getHabits(): Flow<List<Habit>> =
        habitDao.observeActiveHabits().map { list -> list.map { it.toDomain() } }

    override suspend fun getHabitById(id: Long): Habit? =
        habitDao.getById(id)?.toDomain()

    override suspend fun insertHabit(habit: Habit) {
        habitDao.insert(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.update(habit.copy(updatedAt = System.currentTimeMillis()).toEntity())
//        logDao.updateTodaySnapshot(habit.id, habit.goalAmount, LocalDate.now())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit.toEntity())
    }


}
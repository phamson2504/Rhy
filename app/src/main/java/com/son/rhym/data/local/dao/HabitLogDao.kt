package com.son.rhym.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.son.rhym.data.local.entity.HabitLogEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface HabitLogDao {
    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId AND date = :date")
    suspend fun getByDate(habitId: Long, date: LocalDate): HabitLogEntity?

    @Query("SELECT * FROM habit_logs WHERE date = :date")
    fun observeDay(date: LocalDate): Flow<List<HabitLogEntity>>

    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId AND date BETWEEN :from AND :to")
    fun observeRange(habitId: Long, from: LocalDate, to: LocalDate): Flow<List<HabitLogEntity>>

    @Insert
    suspend fun insert(log: HabitLogEntity): Long

    @Update
    suspend fun update(log: HabitLogEntity)

    @Delete
    suspend fun delete(log: HabitLogEntity)

    @Query("UPDATE habit_logs SET goalAmountSnapshot = :goal WHERE habitId = :habitId AND date = :today")
    suspend fun updateTodaySnapshot(habitId: Long, goal: Int, today: LocalDate)
}
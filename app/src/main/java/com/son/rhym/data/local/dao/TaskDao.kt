package com.son.rhym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.son.rhym.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE date = :date AND isActive = 1 ORDER BY createdAt")
    fun observeByDate(date: LocalDate): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Long): TaskEntity?

    @Insert
    suspend fun insert(task: TaskEntity): Long

    @Update
    suspend fun update(task: TaskEntity)

    @Query("UPDATE tasks SET isActive = :active, updatedAt = :now WHERE id = :id")
    suspend fun setActive(id: Long, active: Boolean, now: Long = System.currentTimeMillis())

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteById(id: Long)
}
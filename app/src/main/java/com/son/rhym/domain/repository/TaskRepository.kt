package com.son.rhym.domain.repository

import com.son.rhym.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TaskRepository {
    fun observeTasks(date: LocalDate): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun setActive(id: Long, active: Boolean)
    suspend fun deleteTask(id: Long)
}

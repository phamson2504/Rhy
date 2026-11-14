package com.son.rhym.data.repository

import com.son.rhym.data.local.dao.TaskDao
import com.son.rhym.data.local.mapper.toDomain
import com.son.rhym.data.local.mapper.toEntity
import com.son.rhym.domain.model.Task
import com.son.rhym.domain.repository.TaskRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun observeTasks(date: LocalDate): Flow<List<Task>> =
        dao.observeByDate(date).map { list -> list.map { it.toDomain() } }

    override suspend fun getTaskById(id: Long): Task? =
        dao.getById(id)?.toDomain()

    override suspend fun insertTask(task: Task): Long =
        dao.insert(task.copy(id = 0).toEntity())

    override suspend fun updateTask(task: Task) =
        dao.update(task.copy(updatedAt = System.currentTimeMillis()).toEntity())

    override suspend fun setActive(id: Long, active: Boolean) =
        dao.setActive(id, active)

    override suspend fun deleteTask(id: Long) =
        dao.deleteById(id)
}
package com.son.rhym.data.local.mapper

import com.son.rhym.data.local.entity.TaskEntity
import com.son.rhym.domain.model.Task
import com.son.rhym.ui.create_task.CreateTaskUiState

fun TaskEntity.toDomain() = Task(
    id = id,
    name = name,
    icon = icon,
    color = color,
    reminderTimes = reminderTimes,
    date = date,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Task.toEntity() = TaskEntity(
    id = id,
    name = name,
    icon = icon,
    color = color,
    reminderTimes = reminderTimes,
    date = date,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Task.toUiState() = CreateTaskUiState(
    id = id,
    name = name,
    icon = icon,
    color = color,
    date = date,
    isReminderEnabled = reminderTimes.isNotEmpty(),
    reminderTimes = reminderTimes,
    isEditing = true
)

package com.son.rhym.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Task(
    val id: Long = 0,
    val name: String,
    val icon: String,
    val color: Long,
    val reminderTimes: List<LocalTime> = emptyList(),
    val date: LocalDate,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
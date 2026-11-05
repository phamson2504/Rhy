package com.son.rhym.model

data class HabitEntity(
    val id: Long = 0,
    val name: String,
    val description: String? = null,
    val icon: String,
    val color: Long,
    val goalAmount: Int,
    val goalUnit: String,
    val goalPeriod: GoalPeriod = GoalPeriod.DAY,
    val reminderTimes: List<Int> = emptyList(),
    val startDate: String,
    val endDate: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
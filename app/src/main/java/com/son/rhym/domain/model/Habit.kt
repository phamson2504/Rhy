package com.son.rhym.domain.model

import com.son.rhym.ui.create_habit.GoalType
import com.son.rhym.ui.theme.HabitColors
import java.time.LocalDate
import java.time.LocalTime

data class Habit(
    val id: Long = 0,
    val name: String,
    val description: String?,
    val icon: String,
    val color: Long,
    val goalType: GoalType,
    val goalAmount: Int,
    val goalUnit: String,
    val goalPeriod: GoalPeriod,
    val reminderTimes: List<LocalTime>,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val repeatConfig: RepeatConfig,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

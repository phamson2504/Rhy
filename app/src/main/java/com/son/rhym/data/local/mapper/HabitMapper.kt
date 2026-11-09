package com.son.rhym.data.local.mapper

import com.son.rhym.data.local.entity.HabitEntity
import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.model.RepeatConfig

fun Habit.toEntity() = HabitEntity(
    id = id,
    name = name,
    description = description,
    icon = icon,
    color = color,
    goalType = goalType,
    goalAmount = goalAmount,
    goalUnit = goalUnit,
    goalPeriod = goalPeriod,
    reminderTimes = reminderTimes,
    startDate = startDate,
    endDate = endDate,
    repeatType = repeatConfig.type,
    repeatWeeklyDays = repeatConfig.weeklyDays,
    repeatMonthlyDays = repeatConfig.monthlyDays,
    repeatInterval = repeatConfig.interval,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun HabitEntity.toDomain() = Habit(
    id = id,
    name = name,
    description = description,
    icon = icon,
    color = color,
    goalType = goalType,
    goalAmount = goalAmount,
    goalUnit = goalUnit,
    goalPeriod = goalPeriod,
    reminderTimes = reminderTimes,
    startDate = startDate,
    endDate = endDate,
    repeatConfig = RepeatConfig(
        type = repeatType,
        weeklyDays = repeatWeeklyDays,
        monthlyDays = repeatMonthlyDays,
        interval = repeatInterval
    ),
    createdAt = createdAt,
    updatedAt = updatedAt
)
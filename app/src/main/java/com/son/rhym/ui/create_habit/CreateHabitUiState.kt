package com.son.rhym.ui.create_habit

import com.son.rhym.domain.model.GoalPeriod
import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.ui.theme.HabitColors
import java.time.LocalDate
import java.time.LocalTime

data class CreateHabitUiState(
    val name: String = "",
    val description: String = "",
    val icon: String = "check",
    val color: Long = HabitColors.DEFAULT,
    val goalType: GoalType = GoalType.COUNT,
    val goalAmount: Int = 1,
    val goalUnit: String = "time",
    val goalPeriod: GoalPeriod = GoalPeriod.DAY,
    val repeatConfig: RepeatConfig = RepeatConfig(),
    val reminderTimes: List<LocalTime> = emptyList(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val showGoalDialog: Boolean = false,
    val showIconDialog: Boolean = false,
    val showStartDateDialog: Boolean = false,
    val showEndDateDialog: Boolean = false
) {
    val isReminderEnabled: Boolean get() = reminderTimes.isNotEmpty()
    val isEndDateEnabled: Boolean get() = endDate != null
    val isValid: Boolean get() = name.isNotBlank() && goalAmount >= 1

    fun toDomain() = Habit(
        name = name.trim(),
        description = description.ifBlank { null },
        icon = icon,
        color = color,
        goalType = goalType,
        goalAmount = goalAmount,
        goalUnit = goalUnit,
        goalPeriod = goalPeriod,
        reminderTimes = reminderTimes,
        startDate = startDate ?: LocalDate.now(),
        endDate = endDate,
        repeatConfig = repeatConfig
    )
}

enum class GoalType { COUNT, DURATION }
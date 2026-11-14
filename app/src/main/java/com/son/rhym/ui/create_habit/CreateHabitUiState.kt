package com.son.rhym.ui.create_habit

import androidx.compose.runtime.Immutable
import com.son.rhym.domain.model.Goal
import com.son.rhym.domain.model.GoalPeriod
import com.son.rhym.domain.model.Habit
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.ui.theme.HabitColors
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateHabitUiState(
    val id: Long = 0L,
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
    val startDate: LocalDate? = LocalDate.now(),
    val endDate: LocalDate? = null,
    val isSaving: Boolean = false,
    val isReminderEnabled: Boolean = false,
    val isEndDateEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isEditing: Boolean = false,
) {
    val start: LocalDate get() = startDate ?: LocalDate.now()
    val effectiveEndDate: LocalDate get() = endDate ?: start.plusDays(14)
    val goal: Goal get() = Goal(goalAmount, goalUnit, goalType, goalPeriod)
    val isValid: Boolean get() = name.isNotBlank() && goalAmount >= 1

    fun toDomain() = Habit(
        id = id,
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

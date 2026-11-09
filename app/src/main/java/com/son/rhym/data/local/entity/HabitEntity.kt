package com.son.rhym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.son.rhym.domain.model.GoalPeriod
import com.son.rhym.domain.model.RepeatType
import com.son.rhym.ui.create_habit.GoalType
import com.son.rhym.ui.theme.HabitColors
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val description: String? = null,
    val icon: String = "",
    val color: Long = HabitColors.DEFAULT,
    val goalType: GoalType = GoalType.COUNT,
    val goalAmount: Int = 1,
    val goalUnit: String = "times",
    val goalPeriod: GoalPeriod = GoalPeriod.DAY,
    val repeatType: RepeatType = RepeatType.EVERY_DAY,
    val repeatWeeklyDays: Set<DayOfWeek> = emptySet(),
    val repeatMonthlyDays: Set<Int> = emptySet(),
    val repeatInterval: Int = 1,
    val reminderTimes: List<LocalTime> = emptyList(),
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
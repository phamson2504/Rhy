package com.son.rhym.domain.model

import androidx.compose.runtime.Immutable
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

enum class RepeatType {
    EVERY_DAY,
    WEEKLY,
    MONTHLY,
    TIME_INTERVAL
}

@Immutable
data class RepeatConfig(
    val type: RepeatType = RepeatType.EVERY_DAY,
    val weeklyDays: Set<DayOfWeek> = DayOfWeek.entries.toSet(),
    val monthlyDays: Set<Int> = emptySet(),
    val interval: Int = 1
) {
    fun getDisplayText(): String {
        return when (type) {
            RepeatType.EVERY_DAY -> "Every day"
            RepeatType.WEEKLY -> if (weeklyDays.isEmpty()) "Weekly" else "Weekly (${weeklyDays.joinToString(", ") { it.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) }})"
            RepeatType.MONTHLY -> if (monthlyDays.isEmpty()) "Monthly" else "Monthly (${monthlyDays.sorted().joinToString(", ")})"
            RepeatType.TIME_INTERVAL -> "Repeat Every $interval days"
        }
    }
}


package com.son.rhym.domain.model

import com.son.rhym.data.local.entity.HabitLogEntity
import java.time.LocalDate

data class HabitLog(
    val id: Long = 0,
    val habitId: Long,
    val date: LocalDate,
    val value: Int,
    val goalAmountSnapshot: Int,
    val note: String? = null,
    val completedAt: Long
) {
    val isCompleted get() = value >= goalAmountSnapshot
}

fun HabitLogEntity.toDomain() = HabitLog(id, habitId, date, value, goalAmountSnapshot, note, completedAt)
fun HabitLog.toEntity() = HabitLogEntity(id, habitId, date, value, goalAmountSnapshot, note, completedAt)
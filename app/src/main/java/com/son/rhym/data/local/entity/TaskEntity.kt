package com.son.rhym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.son.rhym.ui.theme.HabitColors
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val icon: String = "",
    val color: Long = HabitColors.DEFAULT,
    val reminderTimes: List<LocalTime> = emptyList(),
    val date: LocalDate = LocalDate.now(),
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

package com.son.rhym.ui.create_task

import androidx.compose.runtime.Immutable
import com.son.rhym.domain.model.Task
import com.son.rhym.ui.theme.HabitColors
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class CreateTaskUiState(
    val id: Long = 0L,
    val name: String = "",
    val icon: String = "check",
    val color: Long = HabitColors.DEFAULT,
    val date: LocalDate = LocalDate.now(),
    val isReminderEnabled: Boolean = false,
    val reminderTimes: List<LocalTime> = emptyList(),
    val isSaving: Boolean = false,
    val isLoading: Boolean = false,
    val isEditing: Boolean = false
) {
    val isValid: Boolean get() = name.isNotBlank()

    fun toDomain(): Task = Task(
        id = id,
        name = name.trim(),
        icon = icon,
        color = color,
        date = date,
        reminderTimes = reminderTimes
    )
}

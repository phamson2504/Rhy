package com.son.rhym.ui.create_habit

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import com.son.rhym.domain.model.Goal
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.ui.create_habit.components.CreateHabitHeader
import com.son.rhym.ui.create_habit.components.EndDateSection
import com.son.rhym.ui.create_habit.components.GoalDialog
import com.son.rhym.ui.create_habit.components.GoalSection
import com.son.rhym.ui.create_habit.components.HabitDescriptionSection
import com.son.rhym.ui.create_habit.components.HabitNameSection
import com.son.rhym.ui.create_habit.components.IconColorPickerDialog
import com.son.rhym.ui.create_habit.components.ReminderSection
import com.son.rhym.ui.create_habit.components.RepeatSection
import com.son.rhym.ui.create_habit.components.SelectDatePicker
import com.son.rhym.ui.create_habit.components.StartDateSection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CreateHabitScreen(
    onBackClick: () -> Unit,
    onRepeatClick: (RepeatConfig) -> Unit,
    repeatConfigFromNav: RepeatConfig? = null,
) {
    val scrollState = rememberScrollState()

    // Single UiState object holding all form data
    var uiState by rememberSaveable { mutableStateOf(CreateHabitUiState()) }

    LaunchedEffect(repeatConfigFromNav) {
        repeatConfigFromNav?.let {
            uiState = uiState.copy(repeatConfig = it)
        }
    }

    var isReminderEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    var isEndDateEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(vertical = 10.dp)
            ) {
                CreateHabitHeader(
                    onBackClick = onBackClick,
                    onSaveClick = {
                        if (!uiState.isValid) return@CreateHabitHeader

                        val finalReminderTimes =
                            if (isReminderEnabled) uiState.reminderTimes else emptyList()

                        val finalEndDate = if (isEndDateEnabled) {
                            uiState.endDate
                                ?: LocalDate.now().plusDays(14)
                        } else null


                        val newHabit = uiState.copy(
                            reminderTimes = finalReminderTimes,
                            endDate = finalEndDate,
                        )

                        Log.d("CreateHabitScreen", "Saved Habit: $newHabit")
                    }
                )

                HabitNameSection(
                    habitName = uiState.name,
                    onHabitNameChange = { newName ->
                        uiState = uiState.copy(name = newName)
                    },
                    icon = uiState.icon,
                    color = uiState.color,
                    onIconClick = {
                        uiState = uiState.copy(showIconDialog = true)
                    }
                )
                if (uiState.showIconDialog) {
                    IconColorPickerDialog(
                        currentIcon = uiState.icon,
                        currentColor = uiState.color,
                        onDismiss = {
                            uiState = uiState.copy(showIconDialog = false)
                        },
                        onConfirm = { selectedIcon, selectedColor ->
                            uiState = uiState.copy(
                                icon = selectedIcon,
                                color = selectedColor,
                                showIconDialog = false
                            )
                        }
                    )
                }

                HabitDescriptionSection(
                    description = uiState.description,
                    onDescriptionChange = { newDescription ->
                        uiState = uiState.copy(description = newDescription)
                    }
                )

                GoalSection(
                    amount = uiState.goalAmount,
                    unit = uiState.goalUnit,
                    period = uiState.goalPeriod.label,
                    onClick = {
                        uiState = uiState.copy(showGoalDialog = true)
                    }
                )

                if (uiState.showGoalDialog) {
                    GoalDialog(
                        initial = Goal(
                            uiState.goalAmount,
                            uiState.goalUnit,
                            uiState.goalType,
                            uiState.goalPeriod
                        ),
                        onDismiss = {
                            uiState = uiState.copy(showGoalDialog = false)
                        },
                        onConfirm = { goal ->
                            uiState = uiState.copy(
                                goalAmount = goal.amount,
                                goalUnit = goal.unit,
                                goalType = goal.goalType,
                                goalPeriod = goal.period,
                                showGoalDialog = false
                            )
                            Log.d("GoalDialog", "Updated UI State: $uiState")
                        }
                    )
                }

                RepeatSection(
                    repeatText = uiState.repeatConfig.getDisplayText(),
                    onclick = { onRepeatClick(uiState.repeatConfig) }
                )

                ReminderSection(
                    isChecked = isReminderEnabled,
                    onCheckedChange = { isReminderEnabled = it },
                    reminderTimes = uiState.reminderTimes,
                    onReminderTimesChange = { newReminderTimes ->
                        uiState = uiState.copy(reminderTimes = newReminderTimes)
                    }
                )

                StartDateSection(
                    startDate = uiState.startDate
                        ?: LocalDate.now(),
                    onClick = {
                        uiState = uiState.copy(showStartDateDialog = true)
                    }
                )

                if (uiState.showStartDateDialog) {
                    SelectDatePicker(
                        currentDate = uiState.startDate
                            ?: LocalDate.now(),
                        onDateSelected = { date ->
                            uiState = uiState.copy(
                                startDate = date,
                                showStartDateDialog = false
                            )
                        },
                        onDismiss = {
                            uiState = uiState.copy(showStartDateDialog = false)
                        }
                    )
                }

                EndDateSection(
                    isChecked = isEndDateEnabled,
                    onCheckedChange = { isEndDateEnabled = it },
                    endDate = uiState.endDate
                        ?: LocalDate.now().plusDays(14),
                    onClick = {
                        uiState = uiState.copy(showEndDateDialog = true)
                    }
                )

                if (uiState.showEndDateDialog) {
                    SelectDatePicker(
                        currentDate = uiState.endDate
                            ?: LocalDate.now().plusDays(7),
                        onDateSelected = { date ->
                            uiState = uiState.copy(
                                endDate = date,
                                showEndDateDialog = false
                            )
                        },
                        onDismiss = {
                            uiState = uiState.copy(showEndDateDialog = false)
                        }
                    )
                }
            }
        }
    }
}

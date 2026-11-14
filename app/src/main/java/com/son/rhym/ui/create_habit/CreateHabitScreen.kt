package com.son.rhym.ui.create_habit

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

private enum class CreateDialog { GOAL, ICON, START_DATE, END_DATE }

@Composable
fun CreateHabitScreen(
    viewModel: CreateHabitViewModel,
    onBackClick: () -> Unit,
    onRepeatClick: () -> Unit,
    onSaved: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Single UiState object holding all form data
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var dialog by rememberSaveable { mutableStateOf<CreateDialog?>(null) }

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
                        viewModel.save(onSuccess = onSaved)
                    }
                )

                HabitNameSection(
                    habitName = uiState.name,
                    onHabitNameChange = viewModel::onNameChange,
                    icon = uiState.icon,
                    color = uiState.color,
                    onIconClick = { dialog = CreateDialog.ICON }
                )

                HabitDescriptionSection(
                    description = uiState.description,
                    onDescriptionChange = viewModel::onDescriptionChange
                )

                GoalSection(
                    amount = uiState.goalAmount,
                    unit = uiState.goalUnit,
                    period = uiState.goalPeriod.label,
                    onClick = { dialog = CreateDialog.GOAL }
                )

                RepeatSection(
                    repeatText = uiState.repeatConfig.getDisplayText(),
                    onclick = onRepeatClick
                )

                ReminderSection(
                    isChecked = uiState.isReminderEnabled,
                    onCheckedChange = viewModel::onReminderToggle,
                    reminderTimes = uiState.reminderTimes,
                    onReminderTimesChange = viewModel::onReminderTimesChange
                )

                StartDateSection(
                    startDate = uiState.start,
                    onClick = { dialog = CreateDialog.START_DATE }
                )

                EndDateSection(
                    isChecked = uiState.isEndDateEnabled,
                    onCheckedChange = viewModel::onEndDateToggle,
                    endDate = uiState.effectiveEndDate,
                    onClick = { dialog = CreateDialog.END_DATE }
                )
            }
        }
    }

    // ---------- Dialogs ----------
    val dismissDialog = { dialog = null }
    when (dialog) {
        CreateDialog.ICON -> IconColorPickerDialog(
            currentIcon = uiState.icon,
            currentColor = uiState.color,
            onDismiss = dismissDialog,
            onConfirm = { icon, color ->
                viewModel.onIconPicked(icon)
                viewModel.onColorChange(color)
                dismissDialog()
            }
        )

        CreateDialog.GOAL -> GoalDialog(
            initial = uiState.goal,
            onDismiss = dismissDialog,
            onConfirm = { goal ->
                viewModel.onGoalConfirmed(goal.goalType, goal.amount, goal.unit, goal.period)
                dismissDialog()
            }
        )

        CreateDialog.START_DATE -> SelectDatePicker(
            currentDate = uiState.start,
            onDateSelected = { date ->
                viewModel.onStartDatePicked(date)
                dismissDialog()
            },
            onDismiss = dismissDialog
        )

        CreateDialog.END_DATE -> SelectDatePicker(
            currentDate = uiState.effectiveEndDate,
            onDateSelected = { date ->
                viewModel.onEndDatePicked(date)
                dismissDialog()
            },
            onDismiss = dismissDialog
        )

        null -> Unit
    }
}

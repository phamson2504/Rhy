package com.son.rhym.ui.create_task

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
import com.son.rhym.ui.create_habit.components.IconColorPickerDialog
import com.son.rhym.ui.create_habit.components.ReminderSection
import com.son.rhym.ui.create_habit.components.SelectDatePicker
import com.son.rhym.ui.create_task.components.DateSection
import com.son.rhym.ui.create_task.components.TaskNameSection

private enum class CreateTaskDialog { ICON, DATE }

@Composable
fun CreateTaskScreen(
    viewModel: CreateTaskViewModel,
    onBackClick: () -> Unit,
    onSaved: () -> Unit
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var dialog by rememberSaveable { mutableStateOf<CreateTaskDialog?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
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
                TaskNameSection(
                    habitName = uiState.name,
                    onHabitNameChange = viewModel::onNameChange,
                    icon = uiState.icon,
                    color = uiState.color,
                    onIconClick = { dialog = CreateTaskDialog.ICON }
                )

                ReminderSection(
                    isChecked = uiState.isReminderEnabled,
                    onCheckedChange = viewModel::onReminderToggle,
                    reminderTimes = uiState.reminderTimes,
                    onReminderTimesChange = viewModel::onReminderTimesChange
                )

                DateSection(
                    startDate = uiState.date,
                    onClick = { dialog = CreateTaskDialog.DATE }
                )
            }
        }
    }

    val dismissDialog = { dialog = null }
    when (dialog) {
        CreateTaskDialog.ICON -> IconColorPickerDialog(
            currentIcon = uiState.icon,
            currentColor = uiState.color,
            onDismiss = dismissDialog,
            onConfirm = { icon, color ->
                viewModel.onIconPicked(icon)
                viewModel.onColorChange(color)
                dismissDialog()
            }
        )

        CreateTaskDialog.DATE -> SelectDatePicker(
            currentDate = uiState.date,
            onDateSelected = { date ->
                viewModel.onDatePicked(date)
                dismissDialog()
            },
            onDismiss = dismissDialog
        )

        null -> Unit
    }
}

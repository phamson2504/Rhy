package com.son.rhym.ui.create_habit.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.time.LocalTime
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderTimePicker(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (LocalTime) -> Unit
) {
    if (showDialog) {
        val timePickerState = rememberTimePickerState(
            initialHour = 8,
            initialMinute = 0,
            is24Hour = false
        )

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text("Select Reminder Time")
            },
            text = {
                TimePicker(
                    state = timePickerState
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(LocalTime.of(timePickerState.hour, timePickerState.minute))
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
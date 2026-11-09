package com.son.rhym.ui.create_habit.repeat.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TimeIntervalSettings(
    interval: Int,
    onIntervalChange: (Int) -> Unit
) {
    OutlinedTextField(
        value = interval.toString(),
        onValueChange = { value ->
            if (value.isEmpty()) {
                onIntervalChange(0)
            } else {
                value.toIntOrNull()?.let {
                    if (it > 0) onIntervalChange(it)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        label = { Text("Repeat every (days)") },
        suffix = { Text("Days") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

    Spacer(modifier = Modifier.height(16.dp))
}
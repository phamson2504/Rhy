package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.son.rhym.model.GoalPeriod

@Composable
fun TimeTabContent(
    timeDigits: String,
    onTimeDigitsChange: (String) -> Unit,
    period: GoalPeriod,
    onPeriodChange: (GoalPeriod) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = timeDigits,
            onValueChange = { onTimeDigitsChange(it.filter(Char::isDigit).take(6)) },
            label = { Text("Time") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
//            visualTransformation = TimeVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        PeriodDropdown(period = period, onPeriodChange = onPeriodChange)
    }
}
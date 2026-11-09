package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.son.rhym.domain.model.GoalPeriod

@Composable
fun TimeTabContent(
    amountTime: String,
    onAmountTimeChange: (String) -> Unit,
    timeUnit: TimeUnitOption,
    onTimeUnitChange: (TimeUnitOption) -> Unit,
    period: GoalPeriod,
    onPeriodChange: (GoalPeriod) -> Unit
) {

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = amountTime,
                onValueChange = { onAmountTimeChange(it.filter(Char::isDigit).take(4)) },
                label = { Text("Goal") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            TimeDropdown(
                unit = timeUnit,
                onUnitChange = onTimeUnitChange,
                modifier = Modifier.weight(1f)
            )
        }
        PeriodDropdown(period = period, onPeriodChange = onPeriodChange)
    }

}
package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.son.rhym.R
import com.son.rhym.domain.model.GoalPeriod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitTabContent(
    amountText: String,
    onAmountChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit,
    period: GoalPeriod,
    onPeriodChange: (GoalPeriod) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Goal
        OutlinedTextField(
            value = amountText,
            onValueChange = {
                onAmountChange(it.filter(Char::isDigit))
            },
            label = { Text(stringResource(R.string.goal)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(12.dp)
        )

        // Unit (Optional)
        OutlinedTextField(
            value = unit,
            onValueChange = onUnitChange,
            label = { Text("Unit (Optional)") },
            singleLine = true,
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(12.dp)
        )
    }

    // Period
    PeriodDropdown(
        period = period,
        onPeriodChange = onPeriodChange
    )

}


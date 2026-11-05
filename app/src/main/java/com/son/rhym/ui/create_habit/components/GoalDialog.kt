package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.son.rhym.model.Goal
import com.son.rhym.model.GoalPeriod

@Composable
fun GoalDialog(
    initial: Goal = Goal(1, "times", GoalPeriod.DAY),
    onDismiss: () -> Unit,
    onConfirm: (Goal) -> Unit
) {
    var text by remember { mutableStateOf(initial.amount.toString()) }
    var unit by remember { mutableStateOf(initial.unit) }
    var period by remember { mutableStateOf(initial.period) }

    val amount = text.toIntOrNull()
    val isValid = amount != null && amount > 0

    var selectedTab by rememberSaveable {
        mutableIntStateOf(0)
    }

    val tabs = listOf("UNIT", "TIME")

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        title = {
            Text("Goal")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SecondaryTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(12.dp)
                        ),
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = {
                                selectedTab = index
                            },
                            text = {
                                Text(
                                    text = title,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> UnitTabContent(
                        amountText = text,
                        onAmountChange = { text = it },
                        unit = unit,
                        onUnitChange = { unit = it },
                        period = period,
                        onPeriodChange = { period = it }
                    )

                    1 -> {
                        TimeTabContent(
                            timeDigits = "",
                            onTimeDigitsChange = { },
                            period = period,
                            onPeriodChange = { period = it }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                enabled = isValid,
                onClick = {
                    onConfirm(
                        Goal(amount!!, unit, period)
                    )
                }
            ) {
                Text("Lưu")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
package com.son.rhym.ui.create_habit.repeat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.domain.model.RepeatType
import java.time.DayOfWeek
import com.son.rhym.ui.create_habit.repeat.components.EveryDay
import com.son.rhym.ui.create_habit.repeat.components.Monthly
import com.son.rhym.ui.create_habit.repeat.components.MonthlyDays
import com.son.rhym.ui.create_habit.repeat.components.RepeatHeader
import com.son.rhym.ui.create_habit.repeat.components.TimeInterval
import com.son.rhym.ui.create_habit.repeat.components.TimeIntervalSettings
import com.son.rhym.ui.create_habit.repeat.components.Weekly
import com.son.rhym.ui.create_habit.repeat.components.WeeklyDays

@Composable
fun RepeatScreen(
    initialConfig: RepeatConfig? = null,
    onBackClick: () -> Unit,
    onConfirm: (RepeatConfig) -> Unit = {}
) {
    var selectedOption by remember {
        mutableStateOf(initialConfig?.type ?: RepeatType.EVERY_DAY)
    }
    var selectedDays by remember {
        mutableStateOf(
            initialConfig?.weeklyDays ?: DayOfWeek.entries.toSet()
        )
    }
    var selectedMonthDays by remember {
        mutableStateOf(initialConfig?.monthlyDays ?: emptySet())
    }
    var interval by remember {
        mutableIntStateOf(initialConfig?.interval ?: 1)
    }

    val scrollState = rememberScrollState()

    fun handleSave() {
        val config = when (selectedOption) {
            RepeatType.EVERY_DAY -> RepeatConfig(
                type = RepeatType.EVERY_DAY,
                weeklyDays = DayOfWeek.entries.toSet(),
                monthlyDays = emptySet(),
                interval = 1
            )
            RepeatType.WEEKLY -> RepeatConfig(
                type = RepeatType.WEEKLY,
                weeklyDays = selectedDays,
                monthlyDays = emptySet(),
                interval = 1
            )
            RepeatType.MONTHLY -> RepeatConfig(
                type = RepeatType.MONTHLY,
                weeklyDays = emptySet(),
                monthlyDays = selectedMonthDays,
                interval = 1
            )
            RepeatType.TIME_INTERVAL -> RepeatConfig(
                type = RepeatType.TIME_INTERVAL,
                weeklyDays = emptySet(),
                monthlyDays = emptySet(),
                interval = interval
            )
        }
        onConfirm(config)
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
                RepeatHeader(
                    onBackClick = onBackClick,
                    onSaveClick = { handleSave() }
                )

                EveryDay(
                    selected = selectedOption == RepeatType.EVERY_DAY,
                    onClick = { selectedOption = RepeatType.EVERY_DAY }
                )

                Weekly(
                    selected = selectedOption == RepeatType.WEEKLY,
                    onClick = { selectedOption = RepeatType.WEEKLY }
                )

                if (selectedOption == RepeatType.WEEKLY) {
                    WeeklyDays(
                        selectedDays = selectedDays,
                        onDaySelected = { day ->
                            selectedDays = if (day in selectedDays) {
                                selectedDays - day
                            } else {
                                selectedDays + day
                            }
                        }
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                Monthly(
                    selected = selectedOption == RepeatType.MONTHLY,
                    onClick = { selectedOption = RepeatType.MONTHLY }
                )

                if (selectedOption == RepeatType.MONTHLY) {
                    MonthlyDays(
                        selectedDays = selectedMonthDays,
                        onDaySelected = { day ->
                            selectedMonthDays = if (day in selectedMonthDays) {
                                selectedMonthDays - day
                            } else {
                                selectedMonthDays + day
                            }
                        }
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                TimeInterval(
                    selected = selectedOption == RepeatType.TIME_INTERVAL,
                    onClick = { selectedOption = RepeatType.TIME_INTERVAL }
                )

                if (selectedOption == RepeatType.TIME_INTERVAL) {
                    TimeIntervalSettings(
                        interval = interval,
                        onIntervalChange = { interval = it }
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}
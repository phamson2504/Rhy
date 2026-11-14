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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.domain.model.RepeatType
import com.son.rhym.ui.create_habit.CreateHabitViewModel
import com.son.rhym.ui.create_habit.repeat.components.EveryDay
import com.son.rhym.ui.create_habit.repeat.components.Monthly
import com.son.rhym.ui.create_habit.repeat.components.MonthlyDays
import com.son.rhym.ui.create_habit.repeat.components.RepeatHeader
import com.son.rhym.ui.create_habit.repeat.components.TimeInterval
import com.son.rhym.ui.create_habit.repeat.components.TimeIntervalSettings
import com.son.rhym.ui.create_habit.repeat.components.Weekly
import com.son.rhym.ui.create_habit.repeat.components.WeeklyDays
import java.time.DayOfWeek

@Composable
fun RepeatScreen(
    viewModel: CreateHabitViewModel,
    onBackClick: () -> Unit,
) {
    val initialConfig = remember { viewModel.uiState.value.repeatConfig }

    var currentConfig by remember  {
        mutableStateOf(initialConfig)
    }

    val scrollState = rememberScrollState()

    fun handleSave(): Boolean {
        when (currentConfig.type) {
            RepeatType.WEEKLY -> {
                if (currentConfig.weeklyDays.isEmpty()) {
                    viewModel.showSnackbarMessage("Vui lòng chọn ít nhất 1 ngày trong tuần!")
                    return false
                }
            }
            RepeatType.MONTHLY -> {
                if (currentConfig.monthlyDays.isEmpty()) {
                    viewModel.showSnackbarMessage("Vui lòng chọn ít nhất 1 ngày trong tháng!")
                    return false
                }
            }
            RepeatType.TIME_INTERVAL -> {
                if (currentConfig.interval < 1) {
                    viewModel.showSnackbarMessage("Số ngày lặp phải lớn hơn 0!")
                    return false
                }
            }
            RepeatType.EVERY_DAY -> {}
        }

        val sanitizedConfig = when (currentConfig.type) {
            RepeatType.EVERY_DAY -> currentConfig.copy(
                weeklyDays = DayOfWeek.entries.toSet(),
                monthlyDays = emptySet(),
                interval = 1
            )

            RepeatType.WEEKLY -> currentConfig.copy(
                monthlyDays = emptySet(),
                interval = 1
            )

            RepeatType.MONTHLY -> currentConfig.copy(
                weeklyDays = emptySet(),
                interval = 1
            )

            RepeatType.TIME_INTERVAL -> currentConfig.copy(
                weeklyDays = emptySet(),
                monthlyDays = emptySet()
            )
        }
        viewModel.onRepeatChange(sanitizedConfig)
        return true
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
                    onBackClick = {
                        if (handleSave()) {
                            onBackClick()
                        }
                    }
                )

                EveryDay(
                    selected = currentConfig.type == RepeatType.EVERY_DAY,
                    onClick = {
                        currentConfig = currentConfig.copy(type = RepeatType.EVERY_DAY)
                    }
                )

                Weekly(
                    selected = currentConfig.type == RepeatType.WEEKLY,
                    onClick = {
                        currentConfig = currentConfig.copy(type = RepeatType.WEEKLY)
                    }
                )

                if (currentConfig.type == RepeatType.WEEKLY) {
                    WeeklyDays(
                        selectedDays = currentConfig.weeklyDays,
                        onDaySelected = { day ->
                            val updatedDays = if (day in currentConfig.weeklyDays) {
                                currentConfig.weeklyDays - day
                            } else {
                                currentConfig.weeklyDays + day
                            }
                            currentConfig = currentConfig.copy(weeklyDays = updatedDays)
                        }
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                Monthly(
                    selected = currentConfig.type == RepeatType.MONTHLY,
                    onClick = {
                        currentConfig = currentConfig.copy(type = RepeatType.MONTHLY)
                    }
                )

                if (currentConfig.type == RepeatType.MONTHLY) {
                    MonthlyDays(
                        selectedDays = currentConfig.monthlyDays,
                        onDaySelected = { day ->
                            val updatedMonthDays = if (day in currentConfig.monthlyDays) {
                                currentConfig.monthlyDays - day
                            } else {
                                currentConfig.monthlyDays + day
                            }
                            currentConfig = currentConfig.copy(monthlyDays = updatedMonthDays)
                        }
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                TimeInterval(
                    selected = currentConfig.type == RepeatType.TIME_INTERVAL,
                    onClick = {
                        currentConfig = currentConfig.copy(type = RepeatType.TIME_INTERVAL)
                    }
                )

                if (currentConfig.type == RepeatType.TIME_INTERVAL) {
                    TimeIntervalSettings(
                        interval = currentConfig.interval,
                        onIntervalChange = { newInterval ->
                            currentConfig = currentConfig.copy(interval = newInterval)
                        }
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

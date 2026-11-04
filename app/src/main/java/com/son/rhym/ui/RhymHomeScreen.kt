package com.son.rhym.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.son.rhym.components.home.BottomNavigationBar
import com.son.rhym.components.home.DailyProgressCard
import com.son.rhym.components.home.HabitBottomSheet
import com.son.rhym.components.home.HabitsHeader
import com.son.rhym.components.home.HeaderSection
import com.son.rhym.components.home.WeeklyCalendarSection
import com.son.rhym.model.HabitItem
import com.son.rhym.swipeable.SwipeActionContainer
import com.son.rhym.ui.theme.AccentGreen
import com.son.rhym.ui.theme.AccentOrange
import com.son.rhym.ui.theme.AccentPurple
import java.time.LocalDate

@Composable
fun RhymHomeScreen(
    modifier: Modifier = Modifier,
    onAddHabitClick: () -> Unit
) {
    var habits by remember {
        mutableStateOf(
            listOf(
                HabitItem(
                    id = "1",
                    title = "Morning walk",
                    subtitle = "7:30 AM - 30 min",
                    accentColor = AccentGreen,
                    progressPercent = 0.60f,
                    progressText = "18 / 30 phút"
                ),
                HabitItem(
                    id = "2",
                    title = "Read 20 pages",
                    subtitle = "Evening - 25 min",
                    accentColor = AccentOrange,
                    isCompleted = false
                ),
                HabitItem(
                    id = "3",
                    title = "Practice Spanish",
                    subtitle = "Lunch break - 15 min",
                    accentColor = AccentPurple,
                    isCompleted = true
                )
            )
        )
    }
    var showHabitSheet by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()
    var selectedDate by rememberSaveable {
        mutableStateOf(LocalDate.now().toString())
    }

    if (showHabitSheet) {
        HabitBottomSheet(
            onDismiss = {
                showHabitSheet = false
            }
        )
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BottomNavigationBar()
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                HeaderSection()

                Spacer(modifier = Modifier.height(20.dp))

                WeeklyCalendarSection(
                    selectedDate = LocalDate.parse(selectedDate),
                    onDateSelected = { date ->
                        selectedDate = date.toString()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DailyProgressCard(
                    completedCount = 2,
                    totalCount = 4,
                    progressPercentage = 50
                )
                Spacer(modifier = Modifier.height(28.dp))

                // Habits Section Header
                HabitsHeader(
                    dateText = "Wed, May 14",
                    onAddHabitClick = {
                        showHabitSheet = true
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Habits List
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    habits.forEach { habit ->
                        SwipeActionContainer(
                            habit = habit,
                            onToggleCompleted = { toggledHabit ->
                                habits = habits.map { h ->
                                    if (h.id == toggledHabit.id) {
                                        h.copy(isCompleted = !h.isCompleted)
                                    } else h
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}
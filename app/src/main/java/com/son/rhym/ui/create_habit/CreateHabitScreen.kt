package com.son.rhym.ui.create_habit

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.son.rhym.ui.create_habit.components.CreateHabitHeader
import com.son.rhym.ui.create_habit.components.EndDateSection
import com.son.rhym.ui.create_habit.components.GoalSection
import com.son.rhym.ui.create_habit.components.HabitNameSection
import com.son.rhym.ui.create_habit.components.ReminderSection
import com.son.rhym.ui.create_habit.components.RepeatSection
import com.son.rhym.ui.create_habit.components.StartDateSection
import com.son.rhym.ui.home.components.BottomNavigationBar

@Composable
fun CreateHabitScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    var habitName by remember { mutableStateOf("") }

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
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                CreateHabitHeader()

                HabitNameSection(
                    habitName = habitName,
                    onHabitNameChange = { habitName = it }
                )
                GoalSection()
                RepeatSection()
                ReminderSection()

                Spacer(modifier = Modifier.height(16.dp))

                StartDateSection()
                EndDateSection()

            }
        }
    }

}

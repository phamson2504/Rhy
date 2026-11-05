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
import com.son.rhym.ui.create_habit.components.GoalDialog
import com.son.rhym.ui.create_habit.components.GoalSection
import com.son.rhym.ui.create_habit.components.HabitDescriptionSection
import com.son.rhym.ui.create_habit.components.HabitNameSection
import com.son.rhym.ui.create_habit.components.ReminderSection
import com.son.rhym.ui.create_habit.components.RepeatSection
import com.son.rhym.ui.create_habit.components.StartDateSection

@Composable
fun CreateHabitScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    var habitName by remember { mutableStateOf("") }

    var showGoalDialog by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
        ) { innerPadding ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(vertical = 10.dp)
            ) {
                CreateHabitHeader(
                    onBackClick = onBackClick,
                    onSaveClick = {
                    }
                )
                HabitNameSection(
                    habitName = habitName,
                    onHabitNameChange = { habitName = it }
                )

                HabitDescriptionSection()

                GoalSection(
                    onClick = {
                        showGoalDialog = true
                    }
                )

                if (showGoalDialog) {
                    GoalDialog(
                        onDismiss = {
                            showGoalDialog = false
                        },
                        onConfirm = { goal ->
                            // Update goal
                            showGoalDialog = false
                        }
                    )
                }

                RepeatSection()

                ReminderSection()

                StartDateSection()

                EndDateSection()

            }
        }
    }

}

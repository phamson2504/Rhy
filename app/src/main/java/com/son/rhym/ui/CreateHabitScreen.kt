package com.son.rhym.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.son.rhym.R
import com.son.rhym.components.create_habit.DateEndSection
import com.son.rhym.components.create_habit.DateStartSection
import com.son.rhym.components.create_habit.GoalSection
import com.son.rhym.components.create_habit.HabitNameSection
import com.son.rhym.components.create_habit.HeaderCreateHabit
import com.son.rhym.components.create_habit.RemindSection
import com.son.rhym.components.create_habit.RepeatSection
import com.son.rhym.components.home.BottomNavigationBar

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
        ) { innerPadding ->

            Column(
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                HeaderCreateHabit()

                HabitNameSection(
                    habitName = habitName,
                    onHabitNameChange = { habitName = it }
                )
                GoalSection()
                RepeatSection()
                RemindSection()

                Spacer(modifier = Modifier.height(16.dp))

                DateStartSection()
                DateEndSection()

            }
        }
    }

}
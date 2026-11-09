package com.son.rhym.ui.create_habit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.ui.graphics.vector.ImageVector

val habitIcons: Map<String, ImageVector> = linkedMapOf(
    "book" to Icons.Outlined.MenuBook,
    "water" to Icons.Outlined.WaterDrop,
    "run" to Icons.Outlined.DirectionsRun,
    "sleep" to Icons.Outlined.Bedtime,
    "fitness" to Icons.Outlined.FitnessCenter,
    "meditate" to Icons.Outlined.SelfImprovement,
    "check" to Icons.Outlined.Check
)

fun iconFor(key: String): ImageVector =
    habitIcons[key] ?: Icons.Outlined.Check
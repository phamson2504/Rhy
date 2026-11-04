package com.son.rhym.model

import androidx.compose.ui.graphics.Color

data class HabitItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentColor: Color,
    val progressPercent: Float? = null,
    val progressText: String? = null,
    val isCompleted: Boolean = false
)
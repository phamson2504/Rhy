package com.son.rhym.domain.model

import com.son.rhym.ui.create_habit.GoalType

data class Goal(
    val amount: Int,
    val unit: String,
    val goalType: GoalType,
    val period: GoalPeriod
)

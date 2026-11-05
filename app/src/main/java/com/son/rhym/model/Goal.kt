package com.son.rhym.model

enum class GoalPeriod(val label: String) {
    DAY("ngày"),WEEK("tuần"), MONTH("tháng"), YEAR("năm")
}

data class Goal(
    val amount: Int,
    val unit: String,
    val period: GoalPeriod
)

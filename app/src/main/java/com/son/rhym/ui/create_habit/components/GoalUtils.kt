package com.son.rhym.ui.create_habit.components

import com.son.rhym.model.GoalPeriod

// "130" -> 90 giây (00:01:30)
fun String.digitsToSeconds(): Int {
    val d = padStart(6, '0')
    val h = d.substring(0, 2).toInt()
    val m = d.substring(2, 4).toInt()
    val s = d.substring(4, 6).toInt()
    return h * 3600 + m * 60 + s
}

// 90 giây -> "130" (dùng để gán lại timeDigits khi sửa goal cũ)
fun Int.secondsToDigits(): String {
    val h = this / 3600
    val m = (this % 3600) / 60
    val s = this % 60
    return "%02d%02d%02d".format(h, m, s).trimStart('0')
}

// 90 giây -> "00:01:30"
fun Int.formatDuration(): String {
    val h = this / 3600
    val m = (this % 3600) / 60
    val s = this % 60
    return "%02d:%02d:%02d".format(h, m, s)
}
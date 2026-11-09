package com.son.rhym.data.local.database

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    // LocalDate? <-> "2026-11-08"
    @TypeConverter
    fun fromDate(d: LocalDate?): String? = d?.toString()

    @TypeConverter
    fun toDate(s: String?): LocalDate? = s?.let(LocalDate::parse)

    // List<LocalTime> <-> "08:00,20:30"
    @TypeConverter
    fun fromTimes(l: List<LocalTime>): String = l.joinToString(",")

    @TypeConverter
    fun toTimes(s: String): List<LocalTime> =
        if (s.isBlank()) emptyList() else s.split(",").map(LocalTime::parse)

    // Set<DayOfWeek> <-> "1,3,5"
    @TypeConverter
    fun fromDays(s: Set<DayOfWeek>): String =
        s.map { it.value }.sorted().joinToString(",")

    @TypeConverter
    fun toDays(s: String): Set<DayOfWeek> =
        if (s.isBlank()) emptySet() else s.split(",").map { DayOfWeek.of(it.toInt()) }.toSet()

    // Set<Int> <-> "1,15,31"
    @TypeConverter
    fun fromInts(s: Set<Int>): String = s.sorted().joinToString(",")

    @TypeConverter
    fun toInts(s: String): Set<Int> =
        if (s.isBlank()) emptySet() else s.split(",").map { it.toInt() }.toSet()
}
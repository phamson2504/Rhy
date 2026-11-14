package com.son.rhym.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.son.rhym.data.local.dao.HabitDao
import com.son.rhym.data.local.dao.HabitLogDao
import com.son.rhym.data.local.dao.TaskDao
import com.son.rhym.data.local.entity.HabitEntity
import com.son.rhym.data.local.entity.HabitLogEntity
import com.son.rhym.data.local.entity.TaskEntity

@Database(
    entities = [HabitEntity::class, HabitLogEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
    abstract fun taskDao(): TaskDao
}
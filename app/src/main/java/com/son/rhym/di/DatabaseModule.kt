package com.son.rhym.di

import android.content.Context
import androidx.room.Room
import com.son.rhym.data.local.dao.HabitDao
import com.son.rhym.data.local.dao.HabitLogDao
import com.son.rhym.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "habit_tracker.db")
            // .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideHabitDao(db: AppDatabase): HabitDao = db.habitDao()
    @Provides
    fun provideHabitLogDao(db: AppDatabase): HabitLogDao = db.habitLogDao()
}
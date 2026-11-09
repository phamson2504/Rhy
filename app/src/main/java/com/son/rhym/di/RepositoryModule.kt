package com.son.rhym.di

import com.son.rhym.data.repository.HabitLogRepositoryImpl
import com.son.rhym.data.repository.HabitRepositoryImpl
import com.son.rhym.domain.repository.HabitLogRepository
import com.son.rhym.domain.repository.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHabitRepository(impl: HabitRepositoryImpl): HabitRepository

    @Binds
    @Singleton
    abstract fun bindHabitLogRepository(impl: HabitLogRepositoryImpl): HabitLogRepository
}
package com.khsbs.trilogy.di.module

import com.khsbs.trilogy.repository.local.AppDatabase
import com.khsbs.trilogy.repository.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class DaoModule {
    @Provides
    fun provideHistoryDao(): HistoryDao = AppDatabase.getDatabase().historyDao()
}
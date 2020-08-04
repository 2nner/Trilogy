package com.khsbs.trilogy.di.module

import com.khsbs.trilogy.repository.local.AppDatabase
import com.khsbs.trilogy.repository.local.HistoryDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    fun provideHistoryDao(): HistoryDao = AppDatabase.getDatabase().historyDao()
}
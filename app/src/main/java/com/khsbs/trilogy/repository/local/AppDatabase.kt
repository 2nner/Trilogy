package com.khsbs.trilogy.repository.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khsbs.trilogy.BaseApplication
import com.khsbs.trilogy.repository.entity.InterpretHistory

@Database(entities = [InterpretHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    BaseApplication.context,
                    AppDatabase::class.java,
                    "history.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
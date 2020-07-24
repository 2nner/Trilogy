package com.khsbs.trilogy.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khsbs.trilogy.repository.entity.InterpretHistory

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(history: InterpretHistory)

    @Query("SELECT * FROM history")
    fun getHistory(): LiveData<List<InterpretHistory>>

    @Delete
    fun deleteHistory(history: InterpretHistory)
}
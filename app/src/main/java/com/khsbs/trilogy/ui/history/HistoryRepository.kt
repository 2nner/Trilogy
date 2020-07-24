package com.khsbs.trilogy.ui.history

import androidx.lifecycle.LiveData
import com.khsbs.trilogy.repository.entity.InterpretHistory
import com.khsbs.trilogy.repository.local.HistoryDao

class HistoryRepository(private val historyDao: HistoryDao) {
    val historyList: LiveData<List<InterpretHistory>> = historyDao.getHistory()

    fun insert(history: InterpretHistory) {
        historyDao.insert(history)
    }
}
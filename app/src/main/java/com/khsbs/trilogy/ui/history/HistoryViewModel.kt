package com.khsbs.trilogy.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khsbs.trilogy.repository.entity.InterpretHistory
import com.khsbs.trilogy.repository.local.AppDatabase

class HistoryViewModel : ViewModel() {
    private val repository = HistoryRepository(
        AppDatabase.getDatabase().historyDao()
    )
    val historyList: LiveData<List<InterpretHistory>> = repository.historyList
}
package com.khsbs.trilogy.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khsbs.trilogy.repository.entity.InterpretHistory
import com.khsbs.trilogy.repository.local.AppDatabase
import com.khsbs.trilogy.repository.local.HistoryDao
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    historyDao: HistoryDao
) : ViewModel() {
    val historyList: LiveData<List<InterpretHistory>> = historyDao.getHistory()
}
package com.khsbs.trilogy.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.ItemHistoryDefaultBinding
import com.khsbs.trilogy.repository.entity.InterpretHistory

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList = emptyList<InterpretHistory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder = HistoryViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history_default, parent, false)
    )

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.item = historyList[position]
    }

    fun updateList(historyItem: List<InterpretHistory>) {
        historyList = historyItem
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(val binding: ItemHistoryDefaultBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.khsbs.trilogy.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.ItemHistoryDefaultBinding
import com.khsbs.trilogy.databinding.ItemHistoryDetailBinding
import com.khsbs.trilogy.repository.entity.InterpretHistory

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var historyList = mutableListOf<Pair<InterpretHistory, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HISTORY_DEFAULT -> HistoryDefaultViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history_default, parent, false)
            )
            else -> HistoryDetailViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history_detail, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = historyList.size

    override fun getItemViewType(position: Int): Int {
        return historyList[position].second
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryDefaultViewHolder -> {
                holder.binding.item = historyList[position].first
                holder.binding.ivHistoryExpand.setOnClickListener {
                    expandItem(position)
                }
            }
            is HistoryDetailViewHolder -> {
                holder.binding.item = historyList[position].first
                holder.binding.ivClose.setOnClickListener {
                    collapseItem(position)
                }
            }
        }
    }

    fun updateList(historyItem: List<InterpretHistory>) {
        if (historyList.isEmpty()) {
            val newHistoryList = mutableListOf<Pair<InterpretHistory, Int>>()
            historyItem.forEach {
                newHistoryList.add(Pair(it, ITEM_HISTORY_DEFAULT))
            }
            historyList.addAll(newHistoryList)
        }
        else {
            val newHistoryList = historyItem.zip(historyList) { x,y -> Pair(x, y.second)}
            historyList.addAll(newHistoryList)
        }
        notifyDataSetChanged()
    }

    fun expandItem(pos: Int) {
        historyList[pos] = Pair(historyList[pos].first, ITEM_HISTORY_EXPANDED)
        notifyItemChanged(pos)
    }

    fun collapseItem(pos: Int) {
        historyList[pos] = Pair(historyList[pos].first, ITEM_HISTORY_DEFAULT)
        notifyItemChanged(pos)
    }

    inner class HistoryDefaultViewHolder(val binding: ItemHistoryDefaultBinding) : RecyclerView.ViewHolder(binding.root)

    inner class HistoryDetailViewHolder(val binding: ItemHistoryDetailBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val ITEM_HISTORY_DEFAULT = 1
        const val ITEM_HISTORY_EXPANDED = 2
    }
}
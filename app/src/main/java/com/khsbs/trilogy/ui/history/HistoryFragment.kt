package com.khsbs.trilogy.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.DialogHistoryListBinding
import com.khsbs.trilogy.ui.custom.FullScreenBottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HistoryFragment : FullScreenBottomSheetDialogFragment() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: DialogHistoryListBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_history_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        with(binding.rvHistoryList) {
            adapter = HistoryAdapter()
            layoutManager = LinearLayoutManager(context)
        }

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.historyList.observe(viewLifecycleOwner, Observer {
            (binding.rvHistoryList.adapter as HistoryAdapter).updateList(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            dismiss()
        }
        return super.onOptionsItemSelected(item)
    }
}
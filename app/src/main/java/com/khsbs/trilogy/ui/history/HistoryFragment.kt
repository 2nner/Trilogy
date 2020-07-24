package com.khsbs.trilogy.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.DialogHistoryListBinding
import com.khsbs.trilogy.ui.custom.FullScreenBottomSheetDialogFragment

class HistoryFragment : FullScreenBottomSheetDialogFragment() {
    private lateinit var binding: DialogHistoryListBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_history_list, container, false)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
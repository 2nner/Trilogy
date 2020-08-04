package com.khsbs.trilogy.ui.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.databinding.DialogSelectLanguageBinding
import com.khsbs.trilogy.ui.main.InterpretViewModel
import com.khsbs.trilogy.R
import com.khsbs.trilogy.ui.custom.FullScreenBottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SelectLanguageFragment : FullScreenBottomSheetDialogFragment() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: DialogSelectLanguageBinding
    private lateinit var viewModel: InterpretViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_select_language, container, false)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(InterpretViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (tag == "select_language_source") {
            binding.targetPoint = "source"
        }
        else if (tag == "select_language_target") {
            binding.targetPoint = "target"
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivGoBack.setOnClickListener {
            dismiss()
        }

        viewModel.dialogEvent.observe(viewLifecycleOwner, Observer {
            dismiss()
        })
    }
}
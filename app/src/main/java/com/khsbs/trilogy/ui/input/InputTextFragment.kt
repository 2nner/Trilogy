package com.khsbs.trilogy.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.DialogInputTextBinding
import com.khsbs.trilogy.ui.custom.FullScreenBottomSheetDialogFragment
import com.khsbs.trilogy.ui.main.InterpretViewModel
import com.khsbs.trilogy.util.multilineIme
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class InputTextFragment : FullScreenBottomSheetDialogFragment() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: DialogInputTextBinding
    private lateinit var viewModel: InterpretViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_input_text, container, false)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(InterpretViewModel::class.java)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.etInputText.multilineIme(EditorInfo.IME_ACTION_SEARCH)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etInputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doInterpret()
                true
            }
            else
                false
        }

        binding.ivGoBack.setOnClickListener {
            dismiss()
        }
    }

    private fun doInterpret() {
        viewModel.interpret()
        binding.interpretResultContainer.visibility = View.VISIBLE
    }
}
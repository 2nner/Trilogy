package com.khsbs.trilogy.ui.input

import android.app.Activity
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.DialogInputTextBinding
import com.khsbs.trilogy.ui.main.InterpretViewModel
import com.khsbs.trilogy.ui.main.InterpretViewModelFactory
import com.khsbs.trilogy.util.multilineIme

class InputTextFragment : BottomSheetDialogFragment() {
    private lateinit var binding: DialogInputTextBinding
    private lateinit var viewModel: InterpretViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_input_text, null, false)
        viewModel = ViewModelProvider(this, InterpretViewModelFactory()).get(InterpretViewModel::class.java)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this

        binding.etInputText.multilineIme(EditorInfo.IME_ACTION_SEARCH)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            setupFullHeight(it as BottomSheetDialog)
        }

        return dialog
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



    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)!!
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams

        layoutParams?.let { it.height = getWindowHeight() }
        bottomSheet.layoutParams = layoutParams
        behavior.state = STATE_EXPANDED
        behavior.skipCollapsed = true
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    private fun doInterpret() {
        viewModel.interpret()
        binding.interpretResultContainer.visibility = View.VISIBLE
    }


}
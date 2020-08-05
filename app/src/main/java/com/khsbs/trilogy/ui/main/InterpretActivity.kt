package com.khsbs.trilogy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.ActivityInterpretBinding
import com.khsbs.trilogy.ui.history.HistoryFragment
import com.khsbs.trilogy.ui.input.InputTextFragment
import com.khsbs.trilogy.ui.language.SelectLanguageFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterpretActivity : AppCompatActivity() {
    // @Inject
    // private lateinit var viewModel: InterpretViewModel
    private val viewModel: InterpretViewModel by viewModels()
    private lateinit var binding: ActivityInterpretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_interpret)
        // viewModel = ViewModelProvider(this).get(InterpretViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        binding.cvInputText.setOnClickListener {
            InputTextFragment().show(supportFragmentManager, "input_text")
        }

        binding.tvSourceLang.setOnClickListener {
            SelectLanguageFragment().show(supportFragmentManager, "select_language_source")
        }

        binding.tvTargetLang.setOnClickListener {
            SelectLanguageFragment().show(supportFragmentManager, "select_language_target")
        }

        binding.tvRecentIcon.setOnClickListener {
            HistoryFragment().show(supportFragmentManager, "history")
        }
    }
}
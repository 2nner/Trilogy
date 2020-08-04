package com.khsbs.trilogy.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.ActivityInterpretBinding
import com.khsbs.trilogy.ui.history.HistoryFragment
import com.khsbs.trilogy.ui.input.InputTextFragment
import com.khsbs.trilogy.ui.language.SelectLanguageFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class InterpretActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private lateinit var viewModel: InterpretViewModel
    private lateinit var binding: ActivityInterpretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_interpret)
        viewModel = ViewModelProvider(this, viewModelFactory).get(InterpretViewModel::class.java)

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
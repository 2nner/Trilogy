package com.khsbs.trilogy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.R
import com.khsbs.trilogy.databinding.ActivityInterpretBinding
import com.khsbs.trilogy.ui.input.InputTextFragment

class InterpretActivity : AppCompatActivity() {

    private lateinit var viewModel: InterpretViewModel
    private lateinit var binding: ActivityInterpretBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_interpret)
        viewModel = ViewModelProvider(this, InterpretViewModelFactory())
            .get(InterpretViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        binding.cvInterpetResult.setOnClickListener {
            InputTextFragment().show(supportFragmentManager, "input_text")
        }
    }
}
package com.dampc.simpleomni.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dampc.simpleomni.R
import com.dampc.simpleomni.application.AppActivity
import com.dampc.simpleomni.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.also {
            it.model.observe(this, Observer { model -> binding.model = model })
            it.alert.observe(this, Observer { alert -> binding.alert = alert })
        }

    }

}
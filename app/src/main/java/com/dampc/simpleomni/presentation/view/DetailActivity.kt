package com.dampc.simpleomni.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dampc.simpleomni.R
import com.dampc.simpleomni.application.AppActivity
import com.dampc.simpleomni.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

const val ARTICLE_DETAIL = "ARTICLE_DETAIL"

@AndroidEntryPoint
class DetailActivity : AppActivity() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        viewModel.also {
            it.detail = intent.getParcelableExtra(ARTICLE_DETAIL)
            it.model.observe(this, Observer { model -> binding.model = model })
        }
    }

}
package com.dampc.simpleomni.presentation

import com.dampc.simpleomni.R
import com.dampc.simpleomni.presentation.model.ArticleItem
import com.dampc.simpleomni.presentation.model.MainModel
import com.dampc.simpleomni.presentation.model.RecyclerViewItem
import com.dampc.simpleomni.presentation.model.TopicItem

object RecyclerViewItems {

    fun getLayoutFor(model: RecyclerViewItem): Int {
        return when (model) {
            is ArticleItem -> R.layout.cell_article
            is TopicItem -> R.layout.cell_topic
            is MainModel.TabModel -> R.layout.view_pager_tab

            else -> throw IllegalStateException("Model $model not supported!")
        }
    }

}
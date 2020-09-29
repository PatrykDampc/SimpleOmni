package com.dampc.simpleomni.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dampc.simpleomni.presentation.RecyclerViewAdapter.BindingHolder
import com.dampc.simpleomni.presentation.RecyclerViewItems.getLayoutFor
import com.dampc.simpleomni.presentation.model.RecyclerViewItem
import java.util.*

open class RecyclerViewAdapter : RecyclerView.Adapter<BindingHolder<ViewDataBinding>>() {

    private val items = ArrayList<RecyclerViewItem>()

    fun setItems(modelItems: List<RecyclerViewItem>?) {
        this.items.clear()
        modelItems?.let { items.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutFor(getItem(position))
    }

    private fun getItem(position: Int): RecyclerViewItem {
        return items[position]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ViewDataBinding> {
        return BindingHolder(onCreateBinding(parent, viewType))
    }

    private fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val inflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(inflater, viewType, parent, false)
    }

    override fun onBindViewHolder(holder: BindingHolder<ViewDataBinding>, position: Int) {
        holder.binding.setVariable(BR.model, getItem(position))
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BindingHolder<out T : ViewDataBinding>(val binding: T) : ViewHolder(binding.root)
}
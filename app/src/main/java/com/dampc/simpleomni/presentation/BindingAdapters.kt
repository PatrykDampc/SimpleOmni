package com.dampc.simpleomni.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dampc.simpleomni.R
import com.dampc.simpleomni.presentation.model.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("items")
    fun RecyclerView.recyclerViewAdapter(items: List<RecyclerViewItem>?) {
        items?.let {
            adapter = (adapter as? RecyclerViewAdapter)
                ?.also { it.setItems(items) }
                ?: RecyclerViewAdapter().apply { setItems(items) }

            layoutManager = LinearLayoutManager(context)
        }

    }

    @JvmStatic
    @BindingAdapter("bindTabLayout", "items")
    fun ViewPager2.viewPagerAdapter(tabLayout: TabLayout?, items: List<MainModel.TabModel>?) {
        tabLayout?.let { tabs ->
            adapter = (adapter as? RecyclerViewAdapter)
                ?.also { it.setItems(items) }
                ?: RecyclerViewAdapter().apply { setItems(items) }

            TabLayoutMediator(tabs, this) { tab, position ->
                items?.getOrNull(position)?.let {
                    tab.text = it.label
                }
            }.attach()
        }
    }

    @JvmStatic
    @BindingAdapter("bindEditText")
    fun EditText.bindEditText(model: InputModel?) {
        model?.let {

            doOnTextChanged { _, _, _, _ ->
                it.error.set(null)
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    it.onSearchAction(text.toString())
                    hideKeyboard(this)
                }
                false
            }

        }
    }

    @JvmStatic
    @BindingAdapter("hideWhenEmpty")
    fun View.hideWhenEmpty(collection: Collection<Any>?) {
        visibility = if (collection.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("showWhenEmpty")
    fun View.showWhenEmpty(collection: Collection<Any>?) {
        visibility = if (collection?.isNotEmpty() == true) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(condition: Boolean?) {
        visibility = if (condition == true) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("onClick")
    fun View.onClick(button: Button?) {
        button?.let {
            setOnClickListener { _ ->
                it.onClick()
            }
        } ?: run {
            setOnClickListener(null)
        }

    }


    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.imageUrl(url: String?) {
        url?.let {
            visibility = View.VISIBLE
            Picasso.get().load(it).into(this)
        } ?: run {
            visibility = View.GONE
        }
    }

    private fun hideKeyboard(view: View) {
        (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }

    @JvmStatic
    @BindingAdapter("errorAlert")
    fun View.bindErrorDialog(alert: Alert?) {
        (getTag(R.id.binding_dialog_error) as Dialog?)?.dismiss()

        alert?.let {
            val dialog = AlertDialog.Builder(context, R.style.AppDialog)
                .setTitle(it.title)
                .setMessage(it.message)
                .setCancelable(false)
                .setPositiveButton(it.positiveButton.text) { dialog, _ ->
                    it.positiveButton.onClick()
                    dialog.dismiss()
                }
                .create()

            setTag(R.id.binding_dialog_error, dialog)
            dialog.show()
        }
    }

}
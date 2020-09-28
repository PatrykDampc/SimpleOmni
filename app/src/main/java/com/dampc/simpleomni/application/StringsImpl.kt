package com.dampc.simpleomni.application

import android.content.Context
import androidx.annotation.StringRes
import com.dampc.simpleomni.domain.Strings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringsImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : Strings {

    override fun get(@StringRes id: Int): String {
        return context.resources.getText(id).toString()
    }

}

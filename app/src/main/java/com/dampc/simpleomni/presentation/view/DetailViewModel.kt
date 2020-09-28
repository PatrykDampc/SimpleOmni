package com.dampc.simpleomni.presentation.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dampc.simpleomni.domain.Navigator
import com.dampc.simpleomni.domain.model.Content
import com.dampc.simpleomni.presentation.model.Button
import com.dampc.simpleomni.presentation.model.Detail

class DetailViewModel @ViewModelInject constructor(
    private val navigator: Navigator
) : ViewModel() {

    var detail: Content? = null

    val model: LiveData<Detail> by lazy {
        val liveData = MutableLiveData<Detail>()

        detail?.let {
            liveData.postValue(
                Detail(
                    back = Button { navigator.goBack() },
                    title = it.title,
                    content = it.text
                )
            )
        }

        liveData
    }

}
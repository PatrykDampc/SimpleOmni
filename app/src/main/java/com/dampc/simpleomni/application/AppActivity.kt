package com.dampc.simpleomni.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dampc.simpleomni.presentation.NavigatorObserver
import javax.inject.Inject

abstract class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorObserver: NavigatorObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(NavigationHandler(this, navigatorObserver))
    }

}
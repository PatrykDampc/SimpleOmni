package com.dampc.simpleomni.application

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dampc.simpleomni.domain.Navigator
import com.dampc.simpleomni.presentation.NavigatorObserver
import com.dampc.simpleomni.presentation.view.ARTICLE_DETAIL
import com.dampc.simpleomni.presentation.view.DetailActivity
import io.reactivex.disposables.Disposables

class NavigationHandler(
    private val activity: AppActivity,
    private val navigatorObserver: NavigatorObserver
) : LifecycleObserver {

    private var navigatorDisposable = Disposables.empty()
    private var navBackDisposables = Disposables.empty()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun start() {
        navigatorDisposable = navigatorObserver.observeNavigation()
            .subscribe { destination -> handleActivityChanging(destination) }

        navBackDisposables = navigatorObserver.observeBack()
            .subscribe { activity.onBackPressed() }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun stop() {
        navigatorDisposable.dispose()
        navBackDisposables.dispose()
    }

    private fun handleActivityChanging(destination: Navigator.Destination) {
        Log.d("NAVIGATION_HANDLER", "Navigator handling $destination destination")
        activity.startActivity(
            when (destination) {
                is Navigator.Destination.Detail ->
                    Intent(activity, DetailActivity::class.java).also {
                        it.putExtra(ARTICLE_DETAIL,destination.detail)
                    }
            }
        )
    }

}
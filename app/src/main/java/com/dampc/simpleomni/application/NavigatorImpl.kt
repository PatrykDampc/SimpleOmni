package com.dampc.simpleomni.application

import com.dampc.simpleomni.presentation.Navigator
import com.dampc.simpleomni.presentation.NavigatorObserver
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor() : NavigatorObserver, Navigator {

    private val navigationEvents = PublishRelay.create<Navigator.Destination>()
    private val backEvents = PublishRelay.create<Unit>()

    override fun goTo(destination: Navigator.Destination) = navigationEvents.accept(destination)
    override fun goBack() = backEvents.accept(Unit)

    override fun observeNavigation(): Observable<Navigator.Destination> = navigationEvents.hide()
    override fun observeBack(): Observable<Unit> = backEvents.hide()

}
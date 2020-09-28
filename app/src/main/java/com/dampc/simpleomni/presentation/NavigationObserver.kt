package com.dampc.simpleomni.presentation

import io.reactivex.Observable

interface NavigatorObserver {

    fun observeNavigation(): Observable<Navigator.Destination>
    fun observeBack(): Observable<Unit>

}
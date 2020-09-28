package com.dampc.simpleomni.presentation

import com.dampc.simpleomni.domain.Navigator
import io.reactivex.Observable

interface NavigatorObserver {

    fun observeNavigation(): Observable<Navigator.Destination>
    fun observeBack(): Observable<Unit>

}
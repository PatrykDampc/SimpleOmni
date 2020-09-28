package com.dampc.simpleomni.application

import com.dampc.simpleomni.domain.Repository
import com.dampc.simpleomni.domain.Strings
import com.dampc.simpleomni.presentation.Navigator
import com.dampc.simpleomni.presentation.NavigatorObserver
import com.dampc.simpleomni.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MainModule {

    @Binds
    abstract fun repository(impl: RepositoryImpl): Repository

    @Binds
    abstract fun strings(impl: StringsImpl): Strings

    @Binds
    abstract fun navigator(impl: NavigatorImpl): Navigator

    @Binds
    abstract fun navigationObserver(impl: NavigatorImpl): NavigatorObserver

}
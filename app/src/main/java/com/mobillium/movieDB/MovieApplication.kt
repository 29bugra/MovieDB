package com.mobillium.movieDB

import android.app.Application
import com.mobillium.movieDB.ui.home.di.homeModule
import com.mobillium.movieDB.core.network.di.movieNetworkModule
import com.mobillium.movieDB.ui.details.di.detailModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    private val appModules = listOf(
        movieNetworkModule, homeModule, detailModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(appModules)
        }
    }
}
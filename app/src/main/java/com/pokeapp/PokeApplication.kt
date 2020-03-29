package com.pokeapp

import android.app.Application
import com.pokeapp.di.pokeModule
import com.pokeapp.util.LogReportingTree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(LogReportingTree())
        }

        startKoin(this, listOf(pokeModule))
    }
}
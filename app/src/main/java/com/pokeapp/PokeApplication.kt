package com.pokeapp

import android.app.Application
import com.pokeapp.di.*
import com.pokeapp.di.intent.intentModule
import com.pokeapp.util.LogReportingTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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

        startKoin {
            modules(
                    intentModule +
                            listOf(
                                    presentationModule,
                                    domainModule,
                                    dataModule,
                                    dataRemoteModule,
                                    dataLocalModule
                            )
            ).androidContext(applicationContext)
        }
    }
}
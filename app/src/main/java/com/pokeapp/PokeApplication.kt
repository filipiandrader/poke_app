package com.pokeapp

import android.app.Application
import com.empresas.util.LogReportingTree
import timber.log.Timber

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(LogReportingTree())
    }
}
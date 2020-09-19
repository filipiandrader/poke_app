package com.pokeapp.util

import timber.log.Timber

/**
 * Created by Filipi Andrade Rocha on 28/01/2020
 */
class LogReportingTree: Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Timber.d(message)
    }
}
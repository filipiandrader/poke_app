package com.pokeapp.di.intent

import androidx.fragment.app.Fragment
import com.pokeapp.feature_main.navigation.home.HomeNavigation
import com.pokeapp.feature_main.navigation.splash.SplashNavigation
import com.pokeapp.intent.navigation.home.HomeNavigationImpl
import com.pokeapp.intent.navigation.splash.SplashNavigationImpl
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val intentMainModule = module {

    factory<SplashNavigation> { (fragment: Fragment) ->
        SplashNavigationImpl(fragment)
    }

    factory<HomeNavigation> { (fragment: Fragment) ->
        HomeNavigationImpl(fragment)
    }
}
package com.pokeapp.intent.navigation

import androidx.fragment.app.Fragment
import com.pokeapp.feature_main.fragment.splash.SplashFragmentDirections
import com.pokeapp.feature_main.navigation.splash.SplashNavigation
import com.pokeapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class SplashNavigationImpl(private val fragment: Fragment) : SplashNavigation {

    override fun navigateToMain() = fragment.navigate(
        SplashFragmentDirections.actionSplashFragmentToHomeFragment()
    )
}
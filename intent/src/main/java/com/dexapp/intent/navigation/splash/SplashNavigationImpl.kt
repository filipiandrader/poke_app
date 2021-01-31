package com.dexapp.intent.navigation.splash

import androidx.fragment.app.Fragment
import com.dexapp.feature_main.fragment.splash.SplashFragmentDirections
import com.dexapp.feature_main.navigation.splash.SplashNavigation
import com.dexapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class SplashNavigationImpl(private val fragment: Fragment) : SplashNavigation {

    override fun navigateToMain() = fragment.navigate(
        SplashFragmentDirections.actionSplashFragmentToHomeFragment()
    )
}
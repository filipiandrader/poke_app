package com.dexapp.intent.navigation.home

import androidx.fragment.app.Fragment
import com.dexapp.feature_main.fragment.home.HomeFragmentDirections
import com.dexapp.feature_main.navigation.home.HomeNavigation
import com.dexapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class HomeNavigationImpl(private val fragment: Fragment) : HomeNavigation {

    override fun navigateToPokedex() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToPokedexNavigation()
    )

    override fun navigateToFavoridex() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToFavoridexNavigation()
    )

    override fun navigateToRegion() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToRegionNavigation()
    )
}
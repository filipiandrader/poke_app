package com.pokeapp.intent.navigation.home

import androidx.fragment.app.Fragment
import com.pokeapp.feature_main.fragment.home.HomeFragmentDirections
import com.pokeapp.feature_main.navigation.home.HomeNavigation
import com.pokeapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class HomeNavigationImpl(private val fragment: Fragment) : HomeNavigation {

    override fun navigateToPokedex() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToPokedexNavigation()
    )

    override fun navigateToFavoriDex() {
//            findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
    }

    override fun navigateToRegion() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToRegionNavigation()
    )
}
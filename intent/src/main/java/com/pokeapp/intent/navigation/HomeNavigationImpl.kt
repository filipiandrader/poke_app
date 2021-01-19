package com.pokeapp.intent.navigation

import androidx.fragment.app.Fragment
import com.pokeapp.feature_main.navigation.home.HomeNavigation

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class HomeNavigationImpl(private val fragment: Fragment) : HomeNavigation {

    override fun navigateToPokedex() {
//            findNavController().navigate(R.id.action_homeFragment_to_pokedexFragment)
    }

    override fun navigateToFavoriDex() {
//            findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
    }

    override fun navigateToRegion() {
        //            findNavController().navigate(R.id.action_homeFragment_to_regionFragment)
    }
}
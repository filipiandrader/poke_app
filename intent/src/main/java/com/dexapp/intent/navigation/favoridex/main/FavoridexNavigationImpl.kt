package com.dexapp.intent.navigation.favoridex.main

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.dexapp.feature_favoridex.fragment.main.FavoridexFragmentDirections
import com.dexapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.dexapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */

class FavoridexNavigationImpl(private val fragment: Fragment) : FavoridexNavigation {

    override fun navigateToHome() {
        fragment.findNavController().popBackStack()
    }

    override fun navigateToPokemonInfo(pokemonInfo: PokemonInfoBinding) = fragment.navigate(
        FavoridexFragmentDirections.actionFavoridexFragmentToFavoridexInfoFragment(pokemonInfo)
    )
}
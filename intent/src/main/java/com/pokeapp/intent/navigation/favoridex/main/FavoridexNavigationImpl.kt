package com.pokeapp.intent.navigation.favoridex.main

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_favoridex.fragment.main.FavoridexFragmentDirections
import com.pokeapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.pokeapp.intent.util.navigate

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
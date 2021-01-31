package com.dexapp.intent.navigation.pokedex

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dexapp.base_presentation.model.pokemon.PokemonBinding
import com.dexapp.feature_pokedex.fragment.main.PokemonFragmentDirections
import com.dexapp.feature_pokedex.navigation.PokedexNavigation
import com.dexapp.intent.util.navigate

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

class PokedexNavigationImpl(private val fragment: Fragment) : PokedexNavigation {

    override fun navigateToPokemonInfo(pokemon: PokemonBinding) = fragment.navigate(
        PokemonFragmentDirections.actionPokemonFragmentToPokemonInfoFragment(pokemon)
    )

    override fun navigateToHome() {
        fragment.findNavController().popBackStack()
    }
}
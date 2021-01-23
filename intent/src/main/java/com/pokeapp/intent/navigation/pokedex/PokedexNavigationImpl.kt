package com.pokeapp.intent.navigation.pokedex

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.base_presentation.model.pokemon.PokemonBinding
import com.pokeapp.feature_pokedex.fragment.main.PokemonFragmentDirections
import com.pokeapp.feature_pokedex.navigation.PokedexNavigation
import com.pokeapp.intent.util.navigate

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
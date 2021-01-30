package com.pokeapp.feature_pokedex.navigation

import com.pokeapp.base_presentation.model.pokemon.PokemonBinding

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

interface PokedexNavigation {

    fun navigateToPokemonInfo(pokemon: PokemonBinding)
    fun navigateToHome()
}
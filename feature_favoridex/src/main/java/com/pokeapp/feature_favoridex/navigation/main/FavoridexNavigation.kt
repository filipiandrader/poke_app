package com.pokeapp.feature_favoridex.navigation.main

import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */

interface FavoridexNavigation {

    fun navigateToPokemonInfo(pokemonInfo: PokemonInfoBinding)

    fun navigateToHome()
}
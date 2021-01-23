package com.pokeapp.feature_pokedex.navigation

import com.pokeapp.base_presentation.model.PokemonBinding

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

interface PokemonInfoNavigation {

    val pokemon: PokemonBinding

    fun navigateToPokedex()
}
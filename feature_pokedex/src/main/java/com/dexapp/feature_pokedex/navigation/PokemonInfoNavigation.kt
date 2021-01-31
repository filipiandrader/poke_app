package com.dexapp.feature_pokedex.navigation

import com.dexapp.base_presentation.model.pokemon.PokemonBinding

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

interface PokemonInfoNavigation {

    val pokemon: PokemonBinding

    fun navigateToPokedex()
}
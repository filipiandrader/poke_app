package com.pokeapp.feature_favoridex.navigation.info

import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding

/*
 * Created by Filipi Andrade Rocha on 29/01/2021.
 */

interface FavoridexInfoNavigation {

    val pokemon: PokemonInfoBinding

    fun navigateToFavoridex()
}
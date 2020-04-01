package com.pokeapp.domain.details

import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
interface PokemonDetailsDataSource {

    fun doFavoritePokemon(pokemon: Pokemon,
                          onSuccess: () -> Unit,
                          onFailure: () -> Unit)

    fun cancelJob()
}
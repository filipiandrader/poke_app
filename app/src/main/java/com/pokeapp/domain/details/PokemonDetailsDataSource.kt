package com.pokeapp.domain.details

import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
interface PokemonDetailsDataSource {

    fun getPokemonInfo(id: Int,
                       onSuccess: (Pokemon) -> Unit,
                       onFailure: (t: Throwable) -> Unit)

    fun doFavouritePokemon(pokemon: Pokemon,
                          onSuccess: () -> Unit,
                          onFailure: () -> Unit)

    fun cancelJob()
}
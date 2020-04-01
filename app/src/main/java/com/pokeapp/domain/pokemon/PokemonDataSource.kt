package com.pokeapp.domain.pokemon

import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonDataSource {

    fun getAllPokemons(offset: Int,
                       onSuccess: (MutableList<Pokemon>) -> Unit,
                       onFailure: (t: Throwable) -> Unit)

    fun cancelJob()
}
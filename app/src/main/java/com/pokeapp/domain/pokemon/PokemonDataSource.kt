package com.pokeapp.domain.pokemon

import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonDataSource {

    fun getAllPokemons(offset: Int,
                       onSuccess: (MutableList<Pokemon>) -> Unit,
                       onFailure: (t: Throwable) -> Unit)

    fun getPokemonByGeneration(id: Int,
                               onSuccess: (MutableList<Pokemon>) -> Unit,
                               onFailure: (t: Throwable) -> Unit)

    fun getAllTypes(onSuccess: (MutableList<Type>) -> Unit,
                    onFailure: () -> Unit)

    fun getPokemonByType(id: Int,
                         onSuccess: (MutableList<Pokemon>) -> Unit,
                         onFailure: (t: Throwable) -> Unit)

    fun cancelJob()
}
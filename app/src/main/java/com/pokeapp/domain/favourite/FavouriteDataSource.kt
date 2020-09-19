package com.pokeapp.domain.favourite

import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type

/**
 * Created by Filipi Andrade on 31/03/2020
 */
interface FavouriteDataSource {

    fun getFavouritePokemon(onSuccess: (MutableList<Pokemon>) -> Unit,
                           onFailure: () -> Unit)

    fun getPokemonByGeneration(region: String,
                               onSuccess: (MutableList<Pokemon>) -> Unit,
                               onFailure: () -> Unit)

    fun getAllTypes(onSuccess: (MutableList<Type>) -> Unit,
                    onFailure: () -> Unit)

    fun getPokemonByType(type: String,
                         onSuccess: (MutableList<Pokemon>) -> Unit,
                         onFailure: () -> Unit)

    fun cancelJob()
}
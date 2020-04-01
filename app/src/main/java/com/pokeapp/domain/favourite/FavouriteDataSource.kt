package com.pokeapp.domain.favourite

import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
interface FavouriteDataSource {

    fun getFavouritePokemon(onSuccess: (MutableList<Pokemon>) -> Unit,
                           onFailure: () -> Unit)

    fun cancelJob()
}
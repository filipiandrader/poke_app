package com.pokeapp.domain.favorite

import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 31/03/2020
 */
interface FavoriteDataSource {

    fun getFavoritePokemon(onSuccess: (MutableList<Pokemon>) -> Unit,
                           onFailure: () -> Unit)

    fun cancelJob()
}
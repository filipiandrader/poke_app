package com.pokeapp.data.datasource.remote

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface TypeRemoteDataSource {

    fun getAllTypes(): Flow<List<Type>>
    fun getPokemonByType(name: String): Flow<List<Pokemon>>
}
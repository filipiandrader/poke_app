package com.dexapp.data.datasource.remote.type

import com.dexapp.domain.model.pokemon.Pokemon
import com.dexapp.domain.model.type.Type
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface TypeRemoteDataSource {

    fun getAllTypes(): Flow<List<Type>>
    fun getPokemonByType(name: String): Flow<List<Pokemon>>
}
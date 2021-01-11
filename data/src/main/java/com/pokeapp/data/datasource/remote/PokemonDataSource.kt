package com.pokeapp.data.datasource.remote

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

interface PokemonDataSource {

    fun getAllPokemons(offset: Int): Flow<List<Pokemon>>

    fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>>

    fun getAllTypes(): Flow<List<Type>>

    fun getPokemonByType(id: Int): Flow<List<Pokemon>>
}
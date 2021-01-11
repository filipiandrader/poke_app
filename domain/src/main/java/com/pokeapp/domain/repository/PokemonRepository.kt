package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonRepository {

    suspend fun getAllPokemon(offset: Int): Flow<List<Pokemon>>

    suspend fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>>

    suspend fun getAllTypes(): Flow<List<Type>>

    suspend fun getPokemonByType(id: Int): Flow<List<HashMap<String, Any>>>
}
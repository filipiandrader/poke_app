package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

interface PokemonLocalRepository {

    suspend fun insert(pokemon: Pokemon): Flow<Unit>

    suspend fun delete(pokemon: Pokemon): Flow<Unit>

    suspend fun getById(id: Int): Flow<Pokemon?>

    suspend fun getAll(): Flow<List<Pokemon>?>

    suspend fun getPokemonByGeneration(region: String): Flow<List<Pokemon>?>

    suspend fun getPokemonByType(type: String): Flow<List<Pokemon>?>
}
package com.pokeapp.data.datasource.local

import com.pokeapp.domain.model.Pokemon

/**
 * Created by Filipi Andrade on 30/03/2020
 */

interface PokemonLocalDataSource {

    suspend fun insert(pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    suspend fun getById(id: Int): Pokemon?

    suspend fun getAll(): List<Pokemon>?

    suspend fun getPokemonByGeneration(region: String): List<Pokemon>?

    suspend fun getPokemonByType(type: String): List<Pokemon>?
}
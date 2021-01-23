package com.pokeapp.data.datasource.remote.generation

import com.pokeapp.domain.model.generation.Generation
import com.pokeapp.domain.model.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 31/03/2020
 */

interface GenerationRemoteDataSource {

    fun getGeneration(): Flow<List<Generation>>
    fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>>
}
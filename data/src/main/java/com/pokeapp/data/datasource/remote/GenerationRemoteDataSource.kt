package com.pokeapp.data.datasource.remote

import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 31/03/2020
 */

interface GenerationRemoteDataSource {

    fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>>
}
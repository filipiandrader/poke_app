package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 04/04/2020
 */
interface PokemonDetailsRepository {

    suspend fun getPokemonInfo(id: Int): Flow<List<Pokemon>>
}
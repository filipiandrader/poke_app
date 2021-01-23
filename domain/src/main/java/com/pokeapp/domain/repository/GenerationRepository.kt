package com.pokeapp.domain.repository

import com.pokeapp.domain.model.generation.Generation
import com.pokeapp.domain.model.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface GenerationRepository {

    fun getGeneration(): Flow<List<Generation>>
    fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>>
    fun getPokemonLikedByGeneration(region: String): Flow<List<Pokemon>?>
}
package com.pokeapp.data.datasource.local

import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 30/03/2020
 */

interface PokemonLocalDataSource {

    fun insert(pokemon: Pokemon): Flow<Unit>

    fun delete(pokemon: Pokemon): Flow<Unit>

    fun getPokemonLikedById(id: Int): Flow<Pokemon?>

    fun getAllPokemonsLiked(): Flow<List<Pokemon>?>

    fun getPokemonLikedByGeneration(region: String): Flow<List<Pokemon>?>

    fun getPokemonLikedByType(type: String): Flow<List<Pokemon>?>
}
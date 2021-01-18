package com.pokeapp.data.datasource.local

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 30/03/2020
 */

interface PokemonLocalDataSource {

    fun insert(pokemon: PokemonInfo): Flow<Unit>

    fun delete(pokemon: PokemonInfo): Flow<Unit>

    fun getPokemonLikedById(id: Int): Flow<PokemonInfo?>

    fun getAllPokemonsLiked(): Flow<List<Pokemon>?>

    fun getPokemonLikedByGeneration(region: String): Flow<List<Pokemon>?>

    fun getPokemonLikedByType(type: String): Flow<List<Pokemon>?>
}
package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.PokemonInfo
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

interface PokemonRepository {

    fun getAllPokemons(offset: Int, previous: Int): Flow<List<Pokemon>>

    fun getPokemonInfo(id: Int): Flow<PokemonInfo>

    fun insert(pokemon: PokemonInfo): Flow<Unit>

    fun delete(pokemon: PokemonInfo): Flow<Unit>

    fun getPokemonLikedById(id: Int): Flow<PokemonInfo?>

    fun getAllPokemonsLiked(): Flow<List<Pokemon>?>
}
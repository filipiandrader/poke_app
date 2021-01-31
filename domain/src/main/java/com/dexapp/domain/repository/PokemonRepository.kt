package com.dexapp.domain.repository

import com.dexapp.domain.model.pokedex.Pokedex
import com.dexapp.domain.model.pokemon.PokemonInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

interface PokemonRepository {

    fun getAllPokemons(offset: Int, previous: Int): Flow<Pokedex>
    fun getPokemonInfo(id: Int): Flow<PokemonInfo>
    fun doLikePokemon(pokemon: PokemonInfo): Flow<Unit>
    fun doDislikePokemon(pokemon: PokemonInfo): Flow<Unit>
    fun getPokemonLikedById(id: Int): Flow<PokemonInfo?>
    fun getAllPokemonsLiked(): Flow<List<PokemonInfo>?>
}
package com.dexapp.data.datasource.local.pokemon

import com.dexapp.domain.model.pokemon.PokemonInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 30/03/2020
 */

interface PokemonLocalDataSource {

    fun doLikePokemon(pokemon: PokemonInfo): Flow<Unit>
    fun doDislikePokemon(pokemon: PokemonInfo): Flow<Unit>
    fun getPokemonLikedById(id: Int): Flow<PokemonInfo?>
    fun getAllPokemonsLiked(): Flow<List<PokemonInfo>?>
    fun getPokemonLikedByGeneration(region: String): Flow<List<PokemonInfo>?>
    fun getPokemonLikedByType(type: String): Flow<List<PokemonInfo>?>
}
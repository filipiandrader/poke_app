package com.dexapp.data.datasource.remote.pokemon

import com.dexapp.domain.model.pokedex.Pokedex
import com.dexapp.domain.model.pokemon.PokemonInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

interface PokemonRemoteDataSource {

    fun getAllPokemons(offset: Int, previous: Int): Flow<Pokedex>
    fun getPokemonInfo(id: Int): Flow<PokemonInfo>
}
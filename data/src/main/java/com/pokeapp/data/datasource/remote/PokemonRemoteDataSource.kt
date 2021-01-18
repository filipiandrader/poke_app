package com.pokeapp.data.datasource.remote

import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.PokemonInfo
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

interface PokemonRemoteDataSource {

    fun getAllPokemons(offset: Int, previous: Int): Flow<List<Pokemon>>

    fun getPokemonInfo(id: Int): Flow<PokemonInfo>
}
package com.pokeapp.domain.repository

import com.pokeapp.domain.model.pokemon.Pokemon
import com.pokeapp.domain.model.pokemon.PokemonInfo
import com.pokeapp.domain.model.type.Type
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface TypeRepository {

    fun getAllTypes(): Flow<List<Type>>
    fun getPokemonByType(name: String): Flow<List<Pokemon>>
    fun insertTypeLocal(type: List<Type>): Flow<Unit>
    fun getAllTypeLocal(): Flow<List<Type>?>
    fun getPokemonLikedByType(type: String): Flow<List<PokemonInfo>?>
}
package com.pokeapp.data.repository.local

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.repository.PokemonLocalRepository
import kotlinx.coroutines.flow.flowOf

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

class PokemonLocalRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonLocalRepository {

    override suspend fun insert(pokemon: Pokemon) = flowOf(pokemonLocalDataSource.insert(pokemon))

    override suspend fun delete(pokemon: Pokemon) = flowOf(pokemonLocalDataSource.delete(pokemon))

    override suspend fun getById(id: Int) = flowOf(pokemonLocalDataSource.getById(id))

    override suspend fun getAll() = flowOf(pokemonLocalDataSource.getAll())

    override suspend fun getPokemonByGeneration(region: String) =
        flowOf(pokemonLocalDataSource.getPokemonByGeneration(region))

    override suspend fun getPokemonByType(type: String) =
        flowOf(pokemonLocalDataSource.getPokemonByType(type))
}
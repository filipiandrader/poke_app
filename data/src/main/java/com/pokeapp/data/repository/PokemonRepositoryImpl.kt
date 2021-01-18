package com.pokeapp.data.repository

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.PokemonRemoteDataSource
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.PokemonInfo
import com.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonRepositoryImpl(
        private val pokemonRemoteDataSource: PokemonRemoteDataSource,
        private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun getAllPokemons(offset: Int, previous: Int) = pokemonRemoteDataSource.getAllPokemons(offset, previous)

    override fun getPokemonInfo(id: Int) = pokemonRemoteDataSource.getPokemonInfo(id)

    override fun insert(pokemon: Pokemon) = pokemonLocalDataSource.insert(pokemon)

    override fun delete(pokemon: Pokemon) = pokemonLocalDataSource.delete(pokemon)

    override fun getPokemonLikedById(id: Int) = pokemonLocalDataSource.getPokemonLikedById(id)

    override fun getAllPokemonsLiked() = pokemonLocalDataSource.getAllPokemonsLiked()
}
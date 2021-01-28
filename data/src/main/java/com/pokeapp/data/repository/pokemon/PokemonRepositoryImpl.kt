package com.pokeapp.data.repository.pokemon

import com.pokeapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.pokemon.PokemonRemoteDataSource
import com.pokeapp.domain.model.pokemon.PokemonInfo
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

    override fun doLikePokemon(pokemon: PokemonInfo) = pokemonLocalDataSource.doLikePokemon(pokemon)

    override fun doDislikePokemon(pokemon: PokemonInfo) = pokemonLocalDataSource.doDislikePokemon(pokemon)

    override fun getPokemonLikedById(id: Int) = pokemonLocalDataSource.getPokemonLikedById(id)

    override fun getAllPokemonsLiked(): Flow<List<PokemonInfo>?> = pokemonLocalDataSource.getAllPokemonsLiked()
}
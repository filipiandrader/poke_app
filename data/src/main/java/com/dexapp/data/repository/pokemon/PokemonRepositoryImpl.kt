package com.dexapp.data.repository.pokemon

import com.dexapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.dexapp.data.datasource.remote.pokemon.PokemonRemoteDataSource
import com.dexapp.data.utils.flatMap
import com.dexapp.domain.model.pokedex.Pokedex
import com.dexapp.domain.model.pokemon.PokemonInfo
import com.dexapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun getAllPokemons(offset: Int, previous: Int) = setupPokemonLiked(offset, previous)

    override fun getPokemonInfo(id: Int) = pokemonRemoteDataSource.getPokemonInfo(id)

    override fun doLikePokemon(pokemon: PokemonInfo) = pokemonLocalDataSource.doLikePokemon(pokemon)

    override fun doDislikePokemon(pokemon: PokemonInfo) = pokemonLocalDataSource.doDislikePokemon(pokemon)

    override fun getPokemonLikedById(id: Int) = pokemonLocalDataSource.getPokemonLikedById(id)

    override fun getAllPokemonsLiked(): Flow<List<PokemonInfo>?> = pokemonLocalDataSource.getAllPokemonsLiked()

    private fun setupPokemonLiked(offset: Int, previous: Int): Flow<Pokedex> = pokemonRemoteDataSource.getAllPokemons(offset, previous).flatMap {
        flow {
            it.pokedex.map { pokemon ->
                val pokemonLocal = getPokemonLikedById(pokemon.id)
                pokemonLocal.collect { pokemonLocalInfo ->
                    pokemon.liked = pokemonLocalInfo?.liked ?: false
                }
            }
            emit(it)
        }
    }
}
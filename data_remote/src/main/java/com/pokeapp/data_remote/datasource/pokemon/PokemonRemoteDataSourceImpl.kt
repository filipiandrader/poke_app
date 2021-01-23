package com.pokeapp.data_remote.datasource.pokemon

import com.pokeapp.data.datasource.remote.pokemon.PokemonRemoteDataSource
import com.pokeapp.data_remote.mapper.pokedex.PokedexMapper
import com.pokeapp.data_remote.mapper.pokemon.PokemonInfoMapper
import com.pokeapp.data_remote.service.pokemon.PokemonService
import com.pokeapp.data_remote.utils.RequestWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 29/03/2020
 */

class PokemonRemoteDataSourceImpl(private val pokemonService: PokemonService) :
        PokemonRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getAllPokemons(offset: Int, previous: Int) = flow {
        emit(
                PokedexMapper.toDomain(
                        requestWrapper.wrapperGenericResponse {
                            pokemonService.getAllPokemon(offset, previous)
                        }.data!!
                )
        )
    }

    override fun getPokemonInfo(id: Int) = flow {
        emit(
                PokemonInfoMapper.toDomain(
                        requestWrapper.wrapperGenericResponse {
                            pokemonService.getPokemonById(id)
                        }.data!!
                )
        )
    }
}
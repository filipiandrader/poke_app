package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.PokemonRemoteDataSource
import com.pokeapp.data_remote.mapper.PokemonInfoMapper
import com.pokeapp.data_remote.mapper.PokemonMapper
import com.pokeapp.data_remote.service.PokemonService
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
                PokemonMapper.listToDomain(
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
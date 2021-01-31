package com.dexapp.data_remote.datasource.pokemon

import com.dexapp.data.datasource.remote.pokemon.PokemonRemoteDataSource
import com.dexapp.data_remote.mapper.pokedex.PokedexMapper
import com.dexapp.data_remote.mapper.pokemon.PokemonInfoMapper
import com.dexapp.data_remote.service.pokemon.PokemonService
import com.dexapp.data_remote.utils.RequestWrapper
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
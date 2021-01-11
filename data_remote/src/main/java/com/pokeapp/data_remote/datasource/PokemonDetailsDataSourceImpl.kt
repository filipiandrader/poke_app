package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.PokemonDetailsDataSource
import com.pokeapp.data_remote.mapper.PokemonMapper
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.data_remote.utils.RequestWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class PokemonDetailsDataSourceImpl(private val pokemonService: PokemonService) :
    PokemonDetailsDataSource, KoinComponent {

        private val requestWrapper: RequestWrapper by inject()

    override fun getPokemonInfo(id: Int) = flow {
        emit(
            PokemonMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    pokemonService.getPokemonById(id)
                }
            )
        )
    }
}
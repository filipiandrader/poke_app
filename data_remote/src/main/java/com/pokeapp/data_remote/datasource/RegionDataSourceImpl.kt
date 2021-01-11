package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.RegionDataSource
import com.pokeapp.data_remote.mapper.RegionMapper
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.data_remote.utils.RequestWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 01/04/2020
 */

class RegionDataSourceImpl(private val pokemonService: PokemonService) :
    RegionDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getRegion() = flow {
        emit(
            requestWrapper.wrapperGenericResponse {
                RegionMapper.toDomain(pokemonService.getRegion())
            }
        )
    }
}
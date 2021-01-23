package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.GenerationRemoteDataSource
import com.pokeapp.data_remote.mapper.GenerationMapper
import com.pokeapp.data_remote.mapper.PokemonMapper
import com.pokeapp.data_remote.service.GenerationService
import com.pokeapp.data_remote.utils.RequestWrapper
import com.pokeapp.domain.model.Generation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class GenerationRemoteDataSourceImpl(private val generationService: GenerationService) :
        GenerationRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getGeneration(): Flow<List<Generation>> = flow {
        emit(
            GenerationMapper.listToDomain(
                requestWrapper.wrapperGenericResponse {
                    generationService.getGeneration()
                }.data!!
            )
        )
    }

    override fun getPokemonByGeneration(id: Int) = flow {
        emit(
                PokemonMapper.listToDomain(
                        requestWrapper.wrapperGenericResponse {
                            generationService.getPokemomByGeneration(id)
                        }.data!!
                )
        )
    }
}
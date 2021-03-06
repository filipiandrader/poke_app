package com.dexapp.data_remote.datasource.generation

import com.dexapp.data.datasource.remote.generation.GenerationRemoteDataSource
import com.dexapp.data_remote.mapper.generation.GenerationMapper
import com.dexapp.data_remote.mapper.pokemon.PokemonMapper
import com.dexapp.data_remote.service.generation.GenerationService
import com.dexapp.data_remote.utils.RequestWrapper
import com.dexapp.domain.model.generation.Generation
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
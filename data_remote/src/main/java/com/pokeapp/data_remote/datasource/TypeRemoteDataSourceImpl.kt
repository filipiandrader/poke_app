package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.TypeRemoteDataSource
import com.pokeapp.data_remote.mapper.PokemonMapper
import com.pokeapp.data_remote.mapper.TypeMapper
import com.pokeapp.data_remote.service.TypeService
import com.pokeapp.data_remote.utils.RequestWrapper
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class TypeRemoteDataSourceImpl(private val typeService: TypeService) : TypeRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getAllTypes() = flow {
        emit(
                TypeMapper.listToDomain(
                        requestWrapper.wrapperGenericResponse {
                            typeService.getType()
                        }.data!!
                )
        )
    }

    override fun getPokemonByType(name: String) = flow {
        emit(
                PokemonMapper.listToDomain(
                        requestWrapper.wrapperGenericResponse {
                            typeService.getPokemonByType(name)
                        }.data!!
                )
        )
    }
}
package com.dexapp.data_remote.datasource.type

import com.dexapp.data.datasource.remote.type.TypeRemoteDataSource
import com.dexapp.data_remote.mapper.pokemon.PokemonMapper
import com.dexapp.data_remote.mapper.type.TypeMapper
import com.dexapp.data_remote.service.type.TypeService
import com.dexapp.data_remote.utils.RequestWrapper
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
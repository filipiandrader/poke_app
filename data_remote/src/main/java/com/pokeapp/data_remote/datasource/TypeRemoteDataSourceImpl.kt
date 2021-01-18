package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.TypeRemoteDataSource
import com.pokeapp.data_remote.service.TypeService
import com.pokeapp.data_remote.utils.RequestWrapper
import com.pokeapp.domain.model.Pokemon
import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class TypeRemoteDataSourceImpl(private val typeService: TypeService) : TypeRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getAllTypes(): Flow<List<Type>> {
        TODO("Not yet implemented")
    }

    override fun getPokemonByType(id: Int): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }
}
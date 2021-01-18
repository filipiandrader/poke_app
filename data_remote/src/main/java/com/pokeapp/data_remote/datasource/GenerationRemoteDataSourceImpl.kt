package com.pokeapp.data_remote.datasource

import com.pokeapp.data.datasource.remote.GenerationRemoteDataSource
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.data_remote.utils.RequestWrapper
import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 31/03/2020
 */

class GenerationRemoteDataSourceImpl(private val pokemonService: PokemonService) :
    GenerationRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getPokemonByGeneration(id: Int): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }
}
package com.pokeapp.data_local.datasource.generation

import com.pokeapp.data.datasource.local.generation.GenerationLocalDataSource
import com.pokeapp.data_local.dao.generation.GenerationDAO
import com.pokeapp.data_local.mapper.generation.GenerationLocalMapper
import com.pokeapp.domain.model.generation.Generation
import kotlinx.coroutines.flow.flow

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

class GenerationLocalDataSourceImpl(private val dao: GenerationDAO) : GenerationLocalDataSource {

    override fun insertGenerationLocal(generation: List<Generation>) = flow {
        generation.map { dao.insert(GenerationLocalMapper.toLocal(it)) }
        emit(Unit)
    }

    override fun getAllGenerationsLocal() = flow {
        emit(GenerationLocalMapper.fromLocal(dao.getGenerations()))
    }
}
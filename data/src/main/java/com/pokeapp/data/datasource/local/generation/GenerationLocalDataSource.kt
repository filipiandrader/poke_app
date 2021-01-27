package com.pokeapp.data.datasource.local.generation

import com.pokeapp.domain.model.generation.Generation
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

interface GenerationLocalDataSource {

    fun insert(generation: Generation): Flow<Unit>
    fun getAllGenerationsLocal(): Flow<List<Generation>?>
}
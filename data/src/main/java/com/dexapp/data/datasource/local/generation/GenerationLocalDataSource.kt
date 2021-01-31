package com.dexapp.data.datasource.local.generation

import com.dexapp.domain.model.generation.Generation
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

interface GenerationLocalDataSource {

    fun insertGenerationLocal(generation: List<Generation>): Flow<Unit>
    fun getAllGenerationsLocal(): Flow<List<Generation>?>
}
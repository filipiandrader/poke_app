package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Region
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionRepository {

    suspend fun getRegion(): Flow<List<Region>>
}
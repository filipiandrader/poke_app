package com.pokeapp.domain.repository

import com.pokeapp.domain.model.Region
import com.pokeapp.domain.model.RegionInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionRepository {

    fun getRegion(): Flow<List<Region>>
    fun getRegionByName(name: String): Flow<RegionInfo>
}
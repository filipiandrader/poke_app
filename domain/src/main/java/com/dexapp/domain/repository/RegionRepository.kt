package com.dexapp.domain.repository

import com.dexapp.domain.model.region.Region
import com.dexapp.domain.model.region.RegionInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionRepository {

    fun getRegion(): Flow<List<Region>>
    fun getRegionByName(name: String): Flow<RegionInfo>
}
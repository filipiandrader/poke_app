package com.pokeapp.data.datasource.remote.region

import com.pokeapp.domain.model.region.Region
import com.pokeapp.domain.model.region.RegionInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionRemoteDataSource {

    fun getRegion(): Flow<List<Region>>
    fun getRegionByName(name: String): Flow<RegionInfo>
}
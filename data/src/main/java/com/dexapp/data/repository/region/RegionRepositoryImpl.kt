package com.dexapp.data.repository.region

import com.dexapp.data.datasource.remote.region.RegionRemoteDataSource
import com.dexapp.domain.repository.RegionRepository

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionRepositoryImpl(private val regionRemoteDataSource: RegionRemoteDataSource) : RegionRepository {

    override fun getRegion() = regionRemoteDataSource.getRegion()

    override fun getRegionByName(name: String) = regionRemoteDataSource.getRegionByName(name)
}
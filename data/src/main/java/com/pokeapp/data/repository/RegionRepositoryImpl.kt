package com.pokeapp.data.repository

import com.pokeapp.data.datasource.remote.RegionRemoteDataSource
import com.pokeapp.domain.model.Region
import com.pokeapp.domain.model.RegionInfo
import com.pokeapp.domain.repository.RegionRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionRepositoryImpl(private val regionRemoteDataSource: RegionRemoteDataSource) : RegionRepository {

    override fun getRegion() = regionRemoteDataSource.getRegion()

    override fun getRegionByName(name: String) = regionRemoteDataSource.getRegionByName(name)
}
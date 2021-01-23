package com.pokeapp.data_remote.datasource.region

import com.pokeapp.data.datasource.remote.region.RegionRemoteDataSource
import com.pokeapp.data_remote.mapper.region.RegionInfoMapper
import com.pokeapp.data_remote.mapper.region.RegionMapper
import com.pokeapp.data_remote.service.region.RegionService
import com.pokeapp.data_remote.utils.RequestWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Filipi Andrade on 01/04/2020
 */

class RegionRemoteDataSourceImpl(private val regionService: RegionService) :
    RegionRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getRegion() = flow {
        emit(
            RegionMapper.listToDomain(
                requestWrapper.wrapperGenericResponse {
                    regionService.getRegion()
                }.data!!
            )
        )
    }

    override fun getRegionByName(name: String) = flow {
        emit(
            RegionInfoMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    regionService.getRegionByName(name)
                }.data!!
            )
        )
    }
}
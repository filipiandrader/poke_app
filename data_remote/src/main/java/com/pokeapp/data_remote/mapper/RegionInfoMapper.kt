package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.RegionInfoResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.RegionInfo

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object RegionInfoMapper : DataRemoteMapper<RegionInfoResponse, RegionInfo>() {

    override fun toDomain(data: RegionInfoResponse) = RegionInfo(
        id = data.id ?: -1,
        mainGeneration = data.mainGeneration ?: "",
        locations = LocationMapper.listToDomain(data.locations),
        groups = GroupMapper.listToDomain(data.groups)
    )
}
package com.dexapp.data_remote.mapper.region

import com.dexapp.data_remote.mapper.group.GroupMapper
import com.dexapp.data_remote.mapper.location.LocationMapper
import com.dexapp.data_remote.model.region.RegionInfoResponse
import com.dexapp.data_remote.utils.DataRemoteMapper
import com.dexapp.domain.model.region.RegionInfo

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
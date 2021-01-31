package com.dexapp.base_presentation.mapper.region

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.mapper.group.GroupMapper
import com.dexapp.base_presentation.mapper.location.LocationMapper
import com.dexapp.base_presentation.model.region.RegionInfoBinding
import com.dexapp.domain.model.region.RegionInfo

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

object RegionInfoMapper : PresentationMapper<RegionInfoBinding, RegionInfo> {

    override fun toDomain(presentation: RegionInfoBinding) = RegionInfo(
        id = presentation.id,
        mainGeneration = presentation.mainGeneration,
        groups = GroupMapper.listToDomain(presentation.groups),
        locations = LocationMapper.listToDomain(presentation.locations)
    )

    override fun fromDomain(domain: RegionInfo) = RegionInfoBinding(
        id = domain.id,
        mainGeneration = domain.mainGeneration,
        groups = GroupMapper.listFromDomain(domain.groups),
        locations = LocationMapper.listFromDomain(domain.locations)
    )
}
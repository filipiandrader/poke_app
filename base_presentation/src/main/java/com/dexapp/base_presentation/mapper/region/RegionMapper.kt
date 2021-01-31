package com.dexapp.base_presentation.mapper.region

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.region.RegionBinding
import com.dexapp.domain.model.region.Region

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

object RegionMapper : PresentationMapper<RegionBinding, Region> {

    fun listFromDomain(regions: List<Region>) = regions.map { fromDomain(it) }

    override fun toDomain(presentation: RegionBinding) = Region(
        name = presentation.name
    )

    override fun fromDomain(domain: Region) = RegionBinding(
        name = domain.name
    )
}
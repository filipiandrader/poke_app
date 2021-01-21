package com.pokeapp.base_presentation.mapper.region.region

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.RegionBinding
import com.pokeapp.domain.model.Region

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
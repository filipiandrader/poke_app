package com.dexapp.base_presentation.mapper.stats

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.stats.StatsBinding
import com.dexapp.domain.model.stats.Stats

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object StatsMapper : PresentationMapper<StatsBinding, Stats> {

    override fun toDomain(presentation: StatsBinding) = Stats(
        baseStat = presentation.baseStat,
        name = presentation.name
    )

    override fun fromDomain(domain: Stats) = StatsBinding(
        baseStat = domain.baseStat,
        name = domain.name
    )
}
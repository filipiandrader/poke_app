package com.pokeapp.base_presentation.mapper.stats

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.StatsBinding
import com.pokeapp.domain.model.Stats

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object StatsMapper : PresentationMapper<StatsBinding, Stats> {

    fun listToDomain(presentation: List<StatsBinding>) = presentation.map { toDomain(it) }

    fun listFromDomain(domain: List<Stats>) = domain.map { fromDomain(it) }

    override fun toDomain(presentation: StatsBinding) = Stats(
        baseStat = presentation.baseStat,
        name = presentation.name
    )

    override fun fromDomain(domain: Stats) = StatsBinding(
        baseStat = domain.baseStat,
        name = domain.name
    )
}
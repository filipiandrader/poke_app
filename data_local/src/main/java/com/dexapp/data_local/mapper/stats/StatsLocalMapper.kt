package com.dexapp.data_local.mapper.stats

import com.dexapp.data_local.mapper.base.DataLocalMapper
import com.dexapp.data_local.model.stats.StatsLocal
import com.dexapp.domain.model.stats.Stats

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object StatsLocalMapper : DataLocalMapper<StatsLocal, Stats> {

    override fun toLocal(domain: Stats) = StatsLocal(
        name = domain.name,
        baseState = domain.baseStat
    )

    override fun fromLocal(local: StatsLocal) = Stats(
        name = local.name,
        baseStat = local.baseState
    )
}
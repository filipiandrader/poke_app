package com.pokeapp.data_local.mapper

import com.pokeapp.data_local.model.StatsLocal
import com.pokeapp.domain.model.Stats

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object StatsLocalMapper {

    fun toStatsList(statsLocal: List<StatsLocal>) = statsLocal.map { toStats(it) }

    private fun toStats(statLocal: StatsLocal) = Stats(
            name = statLocal.name,
            baseState = statLocal.baseState
    )

    fun toStatsLocalList(stats: List<Stats>) = stats.map { toStatsLocal(it) }

    private fun toStatsLocal(stat: Stats) = StatsLocal(
            name = stat.name,
            baseState = stat.baseState
    )
}
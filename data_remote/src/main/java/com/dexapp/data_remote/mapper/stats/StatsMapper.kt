package com.dexapp.data_remote.mapper.stats

import com.dexapp.data_remote.model.stats.StatsResponse
import com.dexapp.data_remote.utils.DataRemoteMapper
import com.dexapp.domain.model.stats.Stats

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object StatsMapper : DataRemoteMapper<StatsResponse, Stats>() {

    fun listToDomain(data: List<StatsResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: StatsResponse) = Stats(
        name = data.name ?: "",
        baseStat = data.baseStat ?: -1
    )
}
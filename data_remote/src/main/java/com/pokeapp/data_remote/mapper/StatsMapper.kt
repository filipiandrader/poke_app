package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.StatsResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Stats

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
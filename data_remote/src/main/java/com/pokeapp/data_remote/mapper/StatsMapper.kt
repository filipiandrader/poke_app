package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.StatsApi
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Stats

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object StatsMapper : DataRemoteMapper<MutableList<StatsApi>, MutableList<Stats>>() {

    override fun toDomain(data: MutableList<StatsApi>) = data.map { toDomain(it) }.toMutableList()

    private fun toDomain(data: StatsApi) = Stats(
        name = data.name ?: "",
        baseState = data.baseState ?: -1
    )
}
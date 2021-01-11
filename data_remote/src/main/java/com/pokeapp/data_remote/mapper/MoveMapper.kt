package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.MoveApi
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Move

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object MoveMapper : DataRemoteMapper<MutableList<MoveApi>, MutableList<Move>>() {

    override fun toDomain(data: MutableList<MoveApi>) = data.map { toDomain(it) }.toMutableList()

    private fun toDomain(data: MoveApi) = Move(
        name = data.name ?: "",
    )
}
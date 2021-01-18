package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.MoveResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Move

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object MoveMapper : DataRemoteMapper<MoveResponse, Move>() {

    fun listToDomain(data: List<MoveResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: MoveResponse) = Move(
        name = data.name ?: "",
    )
}
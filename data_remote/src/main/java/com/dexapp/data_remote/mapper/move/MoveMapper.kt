package com.dexapp.data_remote.mapper.move

import com.dexapp.data_remote.model.move.MoveResponse
import com.dexapp.data_remote.utils.DataRemoteMapper
import com.dexapp.domain.model.move.Move

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object MoveMapper : DataRemoteMapper<MoveResponse, Move>() {

    fun listToDomain(data: List<MoveResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: MoveResponse) = Move(
        name = data.name ?: "",
    )
}
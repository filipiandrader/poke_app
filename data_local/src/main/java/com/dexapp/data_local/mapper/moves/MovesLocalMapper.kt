package com.dexapp.data_local.mapper.moves

import com.dexapp.data_local.mapper.base.DataLocalMapper
import com.dexapp.data_local.model.move.MoveLocal
import com.dexapp.domain.model.move.Move

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object MovesLocalMapper : DataLocalMapper<MoveLocal, Move> {

    override fun toLocal(domain: Move) = MoveLocal(
        name = domain.name
    )

    override fun fromLocal(local: MoveLocal) = Move(
        name = local.name
    )
}
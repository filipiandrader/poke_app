package com.pokeapp.data_local.mapper

import com.pokeapp.data_local.model.MoveLocal
import com.pokeapp.domain.model.Move

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object MovesLocalMapper {

    fun toMovesList(movesLocal: List<MoveLocal>) = movesLocal.map { toMoves(it) }

    private fun toMoves(moveLocal: MoveLocal) = Move(
            name = moveLocal.name
    )

    fun toMovesLocalList(moves: List<Move>) = moves.map { toMovesLocal(it) }

    private fun toMovesLocal(move: Move) = MoveLocal(
            name = move.name
    )
}
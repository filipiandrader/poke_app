package com.pokeapp.base_presentation.mapper.move

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.model.MoveBinding
import com.pokeapp.domain.model.Move

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object MoveMapper : PresentationMapper<MoveBinding, Move> {

    fun listToDomain(presentation: List<MoveBinding>) = presentation.map { toDomain(it) }

    fun listFromDomain(domain: List<Move>) = domain.map { fromDomain(it) }

    override fun toDomain(presentation: MoveBinding) = Move(
        name = presentation.name
    )

    override fun fromDomain(domain: Move) = MoveBinding(
        name = domain.name
    )
}
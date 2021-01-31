package com.dexapp.base_presentation.mapper.move

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.move.MoveBinding
import com.dexapp.domain.model.move.Move

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object MoveMapper : PresentationMapper<MoveBinding, Move> {

    override fun toDomain(presentation: MoveBinding) = Move(name = presentation.name)

    override fun fromDomain(domain: Move) = MoveBinding(name = domain.name)
}
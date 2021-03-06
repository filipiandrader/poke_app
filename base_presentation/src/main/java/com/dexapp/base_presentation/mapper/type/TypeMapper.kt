package com.dexapp.base_presentation.mapper.type

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.type.TypeBinding
import com.dexapp.domain.model.type.Type

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

object TypeMapper : PresentationMapper<TypeBinding, Type> {

    override fun toDomain(presentation: TypeBinding) = Type(name = presentation.name)

    override fun fromDomain(domain: Type) = TypeBinding(name = domain.name)
}
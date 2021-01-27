package com.pokeapp.data_local.mapper.type

import com.pokeapp.data_local.mapper.base.DataLocalMapper
import com.pokeapp.data_local.model.type.TypeLocal
import com.pokeapp.domain.model.type.Type

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object TypeLocalMapper : DataLocalMapper<TypeLocal, Type> {

    override fun toLocal(domain: Type) = TypeLocal(
        name = domain.name
    )

    override fun fromLocal(local: TypeLocal) = Type(
        name = local.name
    )
}
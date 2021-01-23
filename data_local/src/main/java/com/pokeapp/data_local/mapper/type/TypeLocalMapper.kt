package com.pokeapp.data_local.mapper.type

import com.pokeapp.data_local.model.type.TypeLocal
import com.pokeapp.domain.model.type.Type

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object TypeLocalMapper {

    fun toTypeList(typesLocal: List<TypeLocal>) = typesLocal.map { toType(it) }

    private fun toType(typeLocal: TypeLocal) = Type(
        name = typeLocal.name
    )

    fun toTypeLocalList(types: List<Type>) = types.map { toTypeLocal(it) }

    fun toTypeLocal(type: Type) = TypeLocal(
        name = type.name
    )
}
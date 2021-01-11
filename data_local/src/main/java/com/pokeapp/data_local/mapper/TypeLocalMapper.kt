package com.pokeapp.data_local.mapper

import com.pokeapp.data_local.model.TypeLocal
import com.pokeapp.domain.model.Type

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object TypeLocalMapper {

    fun toTypeList(typesLocal: List<TypeLocal>) = typesLocal.map { toType(it) }

    fun toType(typeLocal: TypeLocal) = Type(
        name = typeLocal.name,
        id = typeLocal.id
    )

    fun toTypeLocalList(types: List<Type>) = types.map { toTypeLocal(it) }

    fun toTypeLocal(type: Type) = TypeLocal(
        name = type.name,
        id = type.id
    )
}
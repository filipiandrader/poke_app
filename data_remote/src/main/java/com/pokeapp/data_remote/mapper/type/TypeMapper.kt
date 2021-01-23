package com.pokeapp.data_remote.mapper.type

import com.pokeapp.data_remote.model.type.TypeResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.type.Type

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object TypeMapper : DataRemoteMapper<TypeResponse, Type>() {

    fun listToDomain(data: List<TypeResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: TypeResponse) = Type(
        name = data.name ?: ""
    )
}
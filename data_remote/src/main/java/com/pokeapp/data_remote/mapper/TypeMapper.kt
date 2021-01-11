package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.TypeApi
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Type

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object TypeMapper : DataRemoteMapper<MutableList<TypeApi>, MutableList<Type>>() {

    override fun toDomain(data: MutableList<TypeApi>) = data.map { toDomain(it) }.toMutableList()

    private fun toDomain(data: TypeApi) = Type(
        name = data.name ?: "",
        id = data.url?.toInt() ?: -1
    )
}
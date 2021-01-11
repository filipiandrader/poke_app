package com.pokeapp.data_local.datasource.type

import com.pokeapp.data.datasource.local.TypeLocalDataSource
import com.pokeapp.data_local.dao.PokemonDAO
import com.pokeapp.data_local.mapper.TypeLocalMapper
import com.pokeapp.domain.model.Type

/**
 * Created by Filipi Andrade on 05/04/2020
 */

class TypeLocalDataSourceImpl(private val dao: PokemonDAO) : TypeLocalDataSource {

    override suspend fun insert(type: Type): Boolean {
        dao.insertType(TypeLocalMapper.toTypeLocal(type))
        return true
    }

    override suspend fun getAll() = TypeLocalMapper.toTypeList(dao.getTypes())
}
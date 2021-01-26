package com.pokeapp.data_local.datasource.type

import com.pokeapp.data.datasource.local.type.TypeLocalDataSource
import com.pokeapp.data_local.dao.type.TypeDAO
import com.pokeapp.data_local.mapper.type.TypeLocalMapper
import com.pokeapp.domain.model.type.Type
import kotlinx.coroutines.flow.flow

/**
 * Created by Filipi Andrade on 05/04/2020
 */

class TypeLocalDataSourceImpl(private val dao: TypeDAO) : TypeLocalDataSource {

    override fun insert(type: Type) = flow {
        emit(dao.insertType(TypeLocalMapper.toTypeLocal(type)))
    }

    override fun getAllTypesLocal() = flow {
        emit(TypeLocalMapper.toTypeList(dao.getTypes()))
    }
}
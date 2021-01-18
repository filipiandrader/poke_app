package com.pokeapp.data_local.datasource

import com.pokeapp.data.datasource.local.TypeLocalDataSource
import com.pokeapp.data_local.dao.TypeDAO
import com.pokeapp.data_local.mapper.TypeLocalMapper
import com.pokeapp.domain.model.Type
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
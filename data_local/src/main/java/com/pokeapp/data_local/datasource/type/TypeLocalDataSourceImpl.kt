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

    override fun insertTypeLocal(type: List<Type>) = flow {
        type.map { dao.insertType(TypeLocalMapper.toLocal(it)) }
        emit(Unit)
    }

    override fun getAllTypesLocal() = flow {
        emit(TypeLocalMapper.fromLocal(dao.getTypes()))
    }
}
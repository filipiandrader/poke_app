package com.pokeapp.data.datasource.local

import com.pokeapp.domain.model.Type

/**
 * Created by Filipi Andrade on 05/04/2020
 */

interface TypeLocalDataSource {

    suspend fun insert(type: Type): Boolean

    suspend fun getAll(): List<Type>?
}
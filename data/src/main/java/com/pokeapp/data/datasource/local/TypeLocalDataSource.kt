package com.pokeapp.data.datasource.local

import com.pokeapp.domain.model.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 05/04/2020
 */

interface TypeLocalDataSource {

    fun insert(type: Type): Flow<Unit>

    fun getAllTypesLocal(): Flow<List<Type>?>
}
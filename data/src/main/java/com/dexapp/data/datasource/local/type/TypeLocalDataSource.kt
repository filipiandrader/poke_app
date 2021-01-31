package com.dexapp.data.datasource.local.type

import com.dexapp.domain.model.type.Type
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 05/04/2020
 */

interface TypeLocalDataSource {

    fun insertTypeLocal(type: List<Type>): Flow<Unit>
    fun getAllTypesLocal(): Flow<List<Type>?>
}
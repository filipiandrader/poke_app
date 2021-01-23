package com.pokeapp.data_local.dao.type

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pokeapp.data_local.model.type.TypeLocal

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

@Dao
abstract class TypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertType(vararg type: TypeLocal)

    @Query("SELECT * FROM type")
    abstract suspend fun getTypes(): MutableList<TypeLocal>
}
package com.pokeapp.data_local.dao.generation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pokeapp.data_local.model.generation.GenerationLocal

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

@Dao
abstract class GenerationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg generation: GenerationLocal)

    @Query("SELECT * FROM generation ORDER BY id")
    abstract suspend fun getGenerations(): List<GenerationLocal>
}
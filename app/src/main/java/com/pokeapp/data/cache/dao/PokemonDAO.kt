package com.pokeapp.data.cache.dao

import androidx.room.*
import com.pokeapp.data.cache.entities.PokemonLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Dao
abstract class PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg pokemon: PokemonLocal)

    @Delete
    abstract fun delete(pokemon: PokemonLocal)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    abstract fun getPokemon(id: Int): PokemonLocal?

    @Query("SELECT * FROM pokemon ORDER BY id")
    abstract fun getPokemons(): MutableList<PokemonLocal>
}
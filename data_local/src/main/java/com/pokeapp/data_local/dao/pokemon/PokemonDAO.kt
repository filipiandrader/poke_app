package com.pokeapp.data_local.dao.pokemon

import androidx.room.*
import com.pokeapp.data_local.model.pokemon.PokemonLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Dao
abstract class PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg pokemon: PokemonLocal)

    @Delete
    abstract suspend fun delete(pokemon: PokemonLocal)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    abstract suspend fun getPokemon(id: Int): PokemonLocal?

    @Query("SELECT * FROM pokemon ORDER BY id")
    abstract suspend fun getPokemons(): MutableList<PokemonLocal>
}
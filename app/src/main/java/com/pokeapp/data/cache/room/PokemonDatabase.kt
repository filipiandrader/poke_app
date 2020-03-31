package com.pokeapp.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokeapp.data.cache.dao.PokemonDAO
import com.pokeapp.data.cache.entities.*

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Database(entities = [PokemonLocal::class, Type::class,
    Ability::class, Move::class, Stats::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO
}
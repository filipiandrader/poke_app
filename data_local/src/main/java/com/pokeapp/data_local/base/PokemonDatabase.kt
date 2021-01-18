package com.pokeapp.data_local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokeapp.data_local.dao.PokemonDAO
import com.pokeapp.data_local.dao.TypeDAO
import com.pokeapp.domain.model.*

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Database(entities = [Pokemon::class, Type::class, Ability::class,
    Move::class, Stats::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    abstract fun typeDao(): TypeDAO
}
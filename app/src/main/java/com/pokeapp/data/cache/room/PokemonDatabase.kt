package com.pokeapp.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokeapp.data.cache.dao.PokemonDAO
import com.pokeapp.data.cache.entities.Ability
import com.pokeapp.data.cache.entities.PokemonLocal
import com.pokeapp.data.cache.entities.Type

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Database(entities = [PokemonLocal::class, Type::class, Ability::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO
}
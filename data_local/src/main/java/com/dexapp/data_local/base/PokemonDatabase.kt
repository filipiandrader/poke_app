package com.dexapp.data_local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dexapp.data_local.dao.generation.GenerationDAO
import com.dexapp.data_local.dao.pokemon.PokemonDAO
import com.dexapp.data_local.dao.type.TypeDAO
import com.dexapp.data_local.model.ability.AbilityLocal
import com.dexapp.data_local.model.generation.GenerationLocal
import com.dexapp.data_local.model.move.MoveLocal
import com.dexapp.data_local.model.pokemon.PokemonLocal
import com.dexapp.data_local.model.stats.StatsLocal
import com.dexapp.data_local.model.type.TypeLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Database(
    entities = [PokemonLocal::class, TypeLocal::class, AbilityLocal::class,
        MoveLocal::class, StatsLocal::class, GenerationLocal::class], version = 1, exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    abstract fun typeDao(): TypeDAO

    abstract fun generationDao(): GenerationDAO
}
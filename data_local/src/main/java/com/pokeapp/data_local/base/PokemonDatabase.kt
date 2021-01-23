package com.pokeapp.data_local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokeapp.data_local.dao.pokemon.PokemonDAO
import com.pokeapp.data_local.dao.type.TypeDAO
import com.pokeapp.data_local.model.ability.AbilityLocal
import com.pokeapp.data_local.model.move.MoveLocal
import com.pokeapp.data_local.model.pokemon.PokemonLocal
import com.pokeapp.data_local.model.stats.StatsLocal
import com.pokeapp.data_local.model.type.TypeLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Database(
    entities = [PokemonLocal::class, TypeLocal::class, AbilityLocal::class,
        MoveLocal::class, StatsLocal::class], version = 1, exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    abstract fun typeDao(): TypeDAO
}
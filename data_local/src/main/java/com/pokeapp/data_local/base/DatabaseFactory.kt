package com.pokeapp.data_local.base

import android.app.Application
import androidx.room.Room

/*
 * Created by Filipi Andrade Rocha on 22/09/2020.
 */

fun createRoomDatabase(application: Application) =
        Room.databaseBuilder(application,
                PokemonDatabase::class.java, "pokemon.db")
                .build()

fun providePokemonDao(database: PokemonDatabase) = database.pokemonDao()
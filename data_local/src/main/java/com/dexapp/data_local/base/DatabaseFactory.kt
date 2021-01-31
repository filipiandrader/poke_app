package com.dexapp.data_local.base

import android.app.Application
import androidx.room.Room

/*
 * Created by Filipi Andrade Rocha on 22/09/2020.
 */

object DatabaseFactory {

    fun createRoomDatabase(application: Application) =
            Room.databaseBuilder(application,
                    PokemonDatabase::class.java, "pokemon.db")
                    .build()

    fun providePokemonDao(database: PokemonDatabase) = database.pokemonDao()

    fun provideTypeDao(database: PokemonDatabase) = database.typeDao()

    fun provideGenerationDao(database: PokemonDatabase) = database.generationDao()
}
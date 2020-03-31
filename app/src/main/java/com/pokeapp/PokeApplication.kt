package com.pokeapp

import android.app.Application
import androidx.room.Room
import com.pokeapp.data.cache.room.PokemonDatabase
import com.pokeapp.di.pokeModule
import com.pokeapp.util.LogReportingTree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokeApplication : Application() {

    companion object {
        var database: PokemonDatabase? = null
        const val DB_NAME = "pokemon.db"
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
                PokemonDatabase::class.java,
                DB_NAME)
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(LogReportingTree())
        }

        startKoin(this, listOf(pokeModule))
    }
}
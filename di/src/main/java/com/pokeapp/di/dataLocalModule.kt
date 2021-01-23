package com.pokeapp.di

import com.pokeapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.pokeapp.data.datasource.local.type.TypeLocalDataSource
import com.pokeapp.data_local.base.DatabaseFactory
import com.pokeapp.data_local.datasource.pokemon.PokemonLocalDataSourceImpl
import com.pokeapp.data_local.datasource.type.TypeLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val dataLocalModule = module {

    single { DatabaseFactory.createRoomDatabase(androidApplication()) }

    single { DatabaseFactory.providePokemonDao(get()) }

    single { DatabaseFactory.provideTypeDao(get()) }

    single<PokemonLocalDataSource> { PokemonLocalDataSourceImpl(get()) }

    single<TypeLocalDataSource> { TypeLocalDataSourceImpl(get()) }
}
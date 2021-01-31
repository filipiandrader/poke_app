package com.dexapp.di

import com.dexapp.data.datasource.local.generation.GenerationLocalDataSource
import com.dexapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.dexapp.data.datasource.local.type.TypeLocalDataSource
import com.dexapp.data_local.base.DatabaseFactory
import com.dexapp.data_local.datasource.generation.GenerationLocalDataSourceImpl
import com.dexapp.data_local.datasource.pokemon.PokemonLocalDataSourceImpl
import com.dexapp.data_local.datasource.type.TypeLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val dataLocalModule = module {

    single { DatabaseFactory.createRoomDatabase(androidApplication()) }

    single { DatabaseFactory.providePokemonDao(get()) }

    single { DatabaseFactory.provideTypeDao(get()) }

    single { DatabaseFactory.provideGenerationDao(get()) }

    single<PokemonLocalDataSource> { PokemonLocalDataSourceImpl(get()) }

    single<TypeLocalDataSource> { TypeLocalDataSourceImpl(get()) }

    single<GenerationLocalDataSource> { GenerationLocalDataSourceImpl(get()) }
}
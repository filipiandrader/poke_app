package com.pokeapp.di

import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.data.cache.room.repository.PokemonRoomImpl
import com.pokeapp.data.remote.repository.PokemonRepository
import com.pokeapp.data.remote.repository.PokemonRepositoryImpl
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.data.remote.services.createWebService
import com.pokeapp.data.remote.services.getBaseUrl
import com.pokeapp.domain.PokemonDataSource
import com.pokeapp.domain.PokemonDataSourceImpl
import com.pokeapp.presentation.pokemon.PokemonViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Filipi Andrade on 29/03/2020
 */

val pokeModule = module {
    single { this }

    single { getBaseUrl() }
    single { createWebService<PokemonService>(getBaseUrl()) }

    single { PokemonRoomImpl() as PokemonRoom }

    single { PokemonRepositoryImpl(get(), get()) as PokemonRepository }
    single { PokemonDataSourceImpl(get()) as PokemonDataSource }
    viewModel { PokemonViewModel(get()) }
}
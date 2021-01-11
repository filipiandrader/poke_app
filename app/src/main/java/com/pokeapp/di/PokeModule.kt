@file:Suppress("USELESS_CAST")

package com.pokeapp.di

import com.pokeapp.data_local.datasource.pokemon.PokemonRoom
import com.pokeapp.data_local.datasource.pokemon.PokemonRoomImpl
import com.pokeapp.data_local.datasource.type.TypeRoom
import com.pokeapp.data_local.datasource.type.TypeRoomImpl
import com.pokeapp.data.PokemonDetailsRepository
import com.pokeapp.data.PokemonDetailsRepositoryImpl
import com.pokeapp.data.FavouriteRepository
import com.pokeapp.data.FavouriteRepositoryImpl
import com.pokeapp.data.PokemonRepository
import com.pokeapp.data.PokemonRepositoryImpl
import com.pokeapp.data.RegionRepository
import com.pokeapp.data.RegionRepositoryImpl
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.data_remote.service.createWebService
import com.pokeapp.data_remote.service.getBaseUrl
import com.pokeapp.data_remote.service.getOkHttpClient
import com.pokeapp.data.datasource.remote.PokemonDetailsDataSource
import com.pokeapp.data_remote.datasource.PokemonDetailsDataSourceImpl
import com.pokeapp.data.datasource.remote.FavouriteDataSource
import com.pokeapp.data_remote.datasource.FavouriteDataSourceImpl
import com.pokeapp.data.datasource.remote.PokemonDataSource
import com.pokeapp.data_remote.datasource.PokemonDataSourceImpl
import com.pokeapp.data.datasource.remote.RegionDataSource
import com.pokeapp.data_remote.datasource.RegionDataSourceImpl
import com.pokeapp.presentation.details.PokemonDetailsViewModel
import com.pokeapp.presentation.favourite.FavouriteViewModel
import com.pokeapp.presentation.pokemon.PokemonViewModel
import com.pokeapp.presentation.region.RegionViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * Created by Filipi Andrade on 29/03/2020
 */

val pokeModule = module {

    single { this }

    single { com.pokeapp.data_remote.service.getBaseUrl() }
    single { com.pokeapp.data_remote.service.getOkHttpClient() }
    single<com.pokeapp.data_remote.service.PokemonService> { com.pokeapp.data_remote.service.createWebService(com.pokeapp.data_remote.service.getBaseUrl()) }

    single { PokemonRoomImpl() as PokemonRoom }
    single { TypeRoomImpl() as TypeRoom }

    single<com.pokeapp.data.FavouriteRepository> { com.pokeapp.data.FavouriteRepositoryImpl(get()) }
    single<com.pokeapp.data.datasource.remote.FavouriteDataSource> {
        com.pokeapp.data_remote.datasource.FavouriteDataSourceImpl(
            get(),
            get(),
            get()
        )
    }
    viewModel { FavouriteViewModel(get()) }

    single { com.pokeapp.data.PokemonDetailsRepositoryImpl(get()) as com.pokeapp.data.PokemonDetailsRepository }
    single { com.pokeapp.data_remote.datasource.PokemonDetailsDataSourceImpl(get(), get()) as com.pokeapp.data.datasource.remote.PokemonDetailsDataSource }
    viewModel { PokemonDetailsViewModel(get()) }

    single { com.pokeapp.data.PokemonRepositoryImpl(get(), get()) as com.pokeapp.data.PokemonRepository }
    single { com.pokeapp.data_remote.datasource.PokemonDataSourceImpl(get(), get()) as com.pokeapp.data.datasource.remote.PokemonDataSource }
    viewModel { PokemonViewModel(get()) }

    single { com.pokeapp.data.RegionRepositoryImpl(get()) as com.pokeapp.data.RegionRepository }
    single { com.pokeapp.data_remote.datasource.RegionDataSourceImpl(get()) as com.pokeapp.data.datasource.remote.RegionDataSource }
    viewModel { RegionViewModel(get()) }
}
@file:Suppress("USELESS_CAST")

package com.pokeapp.di

import com.pokeapp.data.cache.room.repository.pokemon.PokemonRoom
import com.pokeapp.data.cache.room.repository.pokemon.PokemonRoomImpl
import com.pokeapp.data.cache.room.repository.type.TypeRoom
import com.pokeapp.data.cache.room.repository.type.TypeRoomImpl
import com.pokeapp.data.remote.repository.details.PokemonDetailsRepository
import com.pokeapp.data.remote.repository.details.PokemonDetailsRepositoryImpl
import com.pokeapp.data.remote.repository.favourite.FavouriteRepository
import com.pokeapp.data.remote.repository.favourite.FavouriteRepositoryImpl
import com.pokeapp.data.remote.repository.pokemon.PokemonRepository
import com.pokeapp.data.remote.repository.pokemon.PokemonRepositoryImpl
import com.pokeapp.data.remote.repository.region.RegionRepository
import com.pokeapp.data.remote.repository.region.RegionRepositoryImpl
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.data.remote.services.createWebService
import com.pokeapp.data.remote.services.getBaseUrl
import com.pokeapp.data.remote.services.getOkHttpClient
import com.pokeapp.domain.details.PokemonDetailsDataSource
import com.pokeapp.domain.details.PokemonDetailsDataSourceImpl
import com.pokeapp.domain.favourite.FavouriteDataSource
import com.pokeapp.domain.favourite.FavouriteDataSourceImpl
import com.pokeapp.domain.pokemon.PokemonDataSource
import com.pokeapp.domain.pokemon.PokemonDataSourceImpl
import com.pokeapp.domain.region.RegionDataSource
import com.pokeapp.domain.region.RegionDataSourceImpl
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

    single { getBaseUrl() }
    single { getOkHttpClient() }
    single<PokemonService> { createWebService(getBaseUrl()) }

    single { PokemonRoomImpl() as PokemonRoom }
    single { TypeRoomImpl() as TypeRoom }

    single<FavouriteRepository> { FavouriteRepositoryImpl(get()) }
    single<FavouriteDataSource> { FavouriteDataSourceImpl(get(), get(), get()) }
    viewModel { FavouriteViewModel(get()) }

    single { PokemonDetailsRepositoryImpl(get()) as PokemonDetailsRepository }
    single { PokemonDetailsDataSourceImpl(get(), get()) as PokemonDetailsDataSource }
    viewModel { PokemonDetailsViewModel(get()) }

    single { PokemonRepositoryImpl(get(), get()) as PokemonRepository }
    single { PokemonDataSourceImpl(get(), get()) as PokemonDataSource }
    viewModel { PokemonViewModel(get()) }

    single { RegionRepositoryImpl(get()) as RegionRepository }
    single { RegionDataSourceImpl(get()) as RegionDataSource }
    viewModel { RegionViewModel(get()) }
}
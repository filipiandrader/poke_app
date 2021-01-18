package com.pokeapp.di

import com.pokeapp.data.datasource.remote.GenerationRemoteDataSource
import com.pokeapp.data.datasource.remote.PokemonRemoteDataSource
import com.pokeapp.data.datasource.remote.RegionRemoteDataSource
import com.pokeapp.data.datasource.remote.TypeRemoteDataSource
import com.pokeapp.data_remote.datasource.GenerationRemoteDataSourceImpl
import com.pokeapp.data_remote.datasource.PokemonRemoteDataSourceImpl
import com.pokeapp.data_remote.datasource.RegionRemoteDataSourceImpl
import com.pokeapp.data_remote.datasource.TypeRemoteDataSourceImpl
import com.pokeapp.data_remote.service.GenerationService
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.data_remote.service.RegionService
import com.pokeapp.data_remote.service.TypeService
import com.pokeapp.data_remote.utils.RequestWrapper
import com.pokeapp.data_remote.utils.RequestWrapperImpl
import com.pokeapp.data_remote.utils.WebServiceFactory
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val dataRemoteModule = module {

    single { WebServiceFactory.provideOkHttpClient() }

    single<PokemonService> { WebServiceFactory.createWebService() }

    single<GenerationService> { WebServiceFactory.createWebService() }

    single<RegionService> { WebServiceFactory.createWebService() }

    single<TypeService> { WebServiceFactory.createWebService() }

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }

    single<GenerationRemoteDataSource> { GenerationRemoteDataSourceImpl(get()) }

    single<RegionRemoteDataSource> { RegionRemoteDataSourceImpl(get()) }

    single<TypeRemoteDataSource> { TypeRemoteDataSourceImpl(get()) }
}
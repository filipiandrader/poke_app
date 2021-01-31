package com.dexapp.di

import com.dexapp.data.datasource.remote.generation.GenerationRemoteDataSource
import com.dexapp.data.datasource.remote.pokemon.PokemonRemoteDataSource
import com.dexapp.data.datasource.remote.region.RegionRemoteDataSource
import com.dexapp.data.datasource.remote.type.TypeRemoteDataSource
import com.dexapp.data_remote.datasource.generation.GenerationRemoteDataSourceImpl
import com.dexapp.data_remote.datasource.pokemon.PokemonRemoteDataSourceImpl
import com.dexapp.data_remote.datasource.region.RegionRemoteDataSourceImpl
import com.dexapp.data_remote.datasource.type.TypeRemoteDataSourceImpl
import com.dexapp.data_remote.service.generation.GenerationService
import com.dexapp.data_remote.service.pokemon.PokemonService
import com.dexapp.data_remote.service.region.RegionService
import com.dexapp.data_remote.service.type.TypeService
import com.dexapp.data_remote.utils.RequestWrapper
import com.dexapp.data_remote.utils.RequestWrapperImpl
import com.dexapp.data_remote.utils.WebServiceFactory
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val dataRemoteModule = module {

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single { WebServiceFactory.provideOkHttpClient() }

    single<PokemonService> { WebServiceFactory.createWebService(get()) }

    single<GenerationService> { WebServiceFactory.createWebService(get()) }

    single<RegionService> { WebServiceFactory.createWebService(get()) }

    single<TypeService> { WebServiceFactory.createWebService(get()) }

    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }

    single<GenerationRemoteDataSource> { GenerationRemoteDataSourceImpl(get()) }

    single<RegionRemoteDataSource> { RegionRemoteDataSourceImpl(get()) }

    single<TypeRemoteDataSource> { TypeRemoteDataSourceImpl(get()) }
}
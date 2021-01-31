package com.dexapp.di

import com.dexapp.data.repository.generation.GenerationRepositoryImpl
import com.dexapp.data.repository.pokemon.PokemonRepositoryImpl
import com.dexapp.data.repository.region.RegionRepositoryImpl
import com.dexapp.data.repository.type.TypeRepositoryImpl
import com.dexapp.domain.repository.GenerationRepository
import com.dexapp.domain.repository.PokemonRepository
import com.dexapp.domain.repository.RegionRepository
import com.dexapp.domain.repository.TypeRepository
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val dataModule = module {

    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }

    single<GenerationRepository> { GenerationRepositoryImpl(get(), get(), get()) }

    single<RegionRepository> { RegionRepositoryImpl(get()) }

    single<TypeRepository> { TypeRepositoryImpl(get(), get(), get()) }
}
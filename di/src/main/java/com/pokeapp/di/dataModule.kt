package com.pokeapp.di

import com.pokeapp.data.repository.generation.GenerationRepositoryImpl
import com.pokeapp.data.repository.pokemon.PokemonRepositoryImpl
import com.pokeapp.data.repository.region.RegionRepositoryImpl
import com.pokeapp.data.repository.type.TypeRepositoryImpl
import com.pokeapp.domain.repository.GenerationRepository
import com.pokeapp.domain.repository.PokemonRepository
import com.pokeapp.domain.repository.RegionRepository
import com.pokeapp.domain.repository.TypeRepository
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
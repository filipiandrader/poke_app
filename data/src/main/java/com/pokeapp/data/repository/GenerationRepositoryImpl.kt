package com.pokeapp.data.repository

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.GenerationRemoteDataSource
import com.pokeapp.domain.repository.GenerationRepository

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class GenerationRepositoryImpl(
        private val generationRemoteDataSource: GenerationRemoteDataSource,
        private val pokemonLocalDataSource: PokemonLocalDataSource
) : GenerationRepository {

    override fun getPokemonByGeneration(id: Int) = generationRemoteDataSource.getPokemonByGeneration(id)

    override fun getPokemonLikedByGeneration(region: String) = pokemonLocalDataSource.getPokemonLikedByGeneration(region)
}
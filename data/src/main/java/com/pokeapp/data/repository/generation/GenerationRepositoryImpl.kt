package com.pokeapp.data.repository.generation

import com.pokeapp.data.datasource.local.generation.GenerationLocalDataSource
import com.pokeapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.generation.GenerationRemoteDataSource
import com.pokeapp.domain.model.generation.Generation
import com.pokeapp.domain.repository.GenerationRepository

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class GenerationRepositoryImpl(
    private val generationRemoteDataSource: GenerationRemoteDataSource,
    private val generationLocalDataSource: GenerationLocalDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : GenerationRepository {

    override fun getGeneration() = generationRemoteDataSource.getGeneration()

    override fun getPokemonByGeneration(id: Int) =
        generationRemoteDataSource.getPokemonByGeneration(id)

    override fun insertGenerationLocal(generation: List<Generation>) =
        generationLocalDataSource.insertGenerationLocal(generation)

    override fun getGenerationLocal() = generationLocalDataSource.getAllGenerationsLocal()

    override fun getPokemonLikedByGeneration(region: String) =
        pokemonLocalDataSource.getPokemonLikedByGeneration(region)
}
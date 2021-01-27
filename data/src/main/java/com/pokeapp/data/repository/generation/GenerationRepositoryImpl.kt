package com.pokeapp.data.repository.generation

import com.pokeapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.generation.GenerationRemoteDataSource
import com.pokeapp.domain.model.pokemon.PokemonInfo
import com.pokeapp.domain.repository.GenerationRepository
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class GenerationRepositoryImpl(
    private val generationRemoteDataSource: GenerationRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : GenerationRepository {

    override fun getGeneration() = generationRemoteDataSource.getGeneration()

    override fun getPokemonByGeneration(id: Int) =
        generationRemoteDataSource.getPokemonByGeneration(id)

    override fun getPokemonLikedByGeneration(region: String): Flow<List<PokemonInfo>?> =
        pokemonLocalDataSource.getPokemonLikedByGeneration(region)
}
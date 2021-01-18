package com.pokeapp.data.repository

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.data.datasource.remote.TypeRemoteDataSource
import com.pokeapp.domain.repository.TypeRepository

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class TypeRepositoryImpl(
        private val typeRemoteDataSource: TypeRemoteDataSource,
        private val pokemonLocalDataSource: PokemonLocalDataSource
) : TypeRepository {

    override fun getAllTypes() = typeRemoteDataSource.getAllTypes()

    override fun getPokemonByType(name: String) = typeRemoteDataSource.getPokemonByType(name)

    override fun getPokemonLikedByType(type: String) = pokemonLocalDataSource.getPokemonLikedByType(type)
}
package com.dexapp.data.repository.type

import com.dexapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.dexapp.data.datasource.local.type.TypeLocalDataSource
import com.dexapp.data.datasource.remote.type.TypeRemoteDataSource
import com.dexapp.domain.model.pokemon.PokemonInfo
import com.dexapp.domain.model.type.Type
import com.dexapp.domain.repository.TypeRepository
import kotlinx.coroutines.flow.Flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

class TypeRepositoryImpl(
    private val typeRemoteDataSource: TypeRemoteDataSource,
    private val typeLocalDataSource: TypeLocalDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : TypeRepository {

    override fun getAllTypes() = typeRemoteDataSource.getAllTypes()

    override fun getPokemonByType(name: String) = typeRemoteDataSource.getPokemonByType(name)

    override fun insertTypeLocal(type: List<Type>) = typeLocalDataSource.insertTypeLocal(type)

    override fun getAllTypeLocal() = typeLocalDataSource.getAllTypesLocal()

    override fun getPokemonLikedByType(type: String): Flow<List<PokemonInfo>?> =
        pokemonLocalDataSource.getPokemonLikedByType(type)
}
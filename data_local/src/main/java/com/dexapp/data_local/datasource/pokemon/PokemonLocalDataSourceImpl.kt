package com.dexapp.data_local.datasource.pokemon

import com.dexapp.data.datasource.local.pokemon.PokemonLocalDataSource
import com.dexapp.data.utils.flatMap
import com.dexapp.data_local.dao.pokemon.PokemonDAO
import com.dexapp.data_local.mapper.pokemon.PokemonLocalMapper
import com.dexapp.data_local.model.pokemon.PokemonLocal
import com.dexapp.domain.model.pokemon.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class PokemonLocalDataSourceImpl(private val dao: PokemonDAO) : PokemonLocalDataSource {

    override fun doLikePokemon(pokemon: PokemonInfo) = flow {
        emit(dao.insert(PokemonLocalMapper.toLocal(pokemon)))
    }

    override fun doDislikePokemon(pokemon: PokemonInfo) = flow {
        emit(dao.delete(PokemonLocalMapper.toLocal(pokemon)))
    }

    override fun getPokemonLikedById(id: Int): Flow<PokemonInfo?> = flow {
        emit(PokemonLocalMapper.fromLocal(dao.getPokemon(id) ?: PokemonLocal()))
    }

    override fun getAllPokemonsLiked() = flow {
        emit(PokemonLocalMapper.fromLocal(dao.getPokemons()))
    }

    override fun getPokemonLikedByGeneration(region: String) = getAllPokemonsLiked().flatMap { list ->
        val pokemons = mutableListOf<PokemonInfo>()
        if (list.isNotEmpty()) {
            list.map { p ->
                if (p.generationName.equals(region, ignoreCase = true)) {
                    pokemons.add(p)
                }
            }
        }
        flowOf(pokemons)
    }

    override fun getPokemonLikedByType(type: String): Flow<List<PokemonInfo>?> = getAllPokemonsLiked().flatMap { list ->
        val pokemons = mutableListOf<PokemonInfo>()
        if (list.isNotEmpty()) {
            list.map { p ->
                p.types.map { t ->
                    if (t.name.equals(type, ignoreCase = true)) {
                        pokemons.add(p)
                    }
                }
            }
        }
        flowOf(pokemons)
    }
}
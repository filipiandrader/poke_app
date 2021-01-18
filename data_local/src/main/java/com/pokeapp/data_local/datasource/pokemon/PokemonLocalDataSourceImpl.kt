package com.pokeapp.data_local.datasource.pokemon

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.data.utils.flatMap
import com.pokeapp.data_local.dao.PokemonDAO
import com.pokeapp.data_local.mapper.PokemonLocalMapper
import com.pokeapp.data_local.model.PokemonLocal
import com.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class PokemonLocalDataSourceImpl(private val dao: PokemonDAO) : PokemonLocalDataSource {

    override fun insert(pokemon: Pokemon) = flow {
        emit(
                dao.insert(PokemonLocalMapper.toSaveLocal(pokemon))
        )
    }

    override fun delete(pokemon: Pokemon) = flow {
        emit(
                dao.delete(PokemonLocalMapper.toSaveLocal(pokemon))
        )
    }

    override fun getPokemonLikedById(id: Int) = flow {
        emit(
                PokemonLocalMapper.toDomain(dao.getPokemon(id) ?: PokemonLocal())
        )
    }

    override fun getAllPokemonsLiked() = flow {
        emit(
                PokemonLocalMapper.toDomainList(dao.getPokemons())
        )
    }

    override fun getPokemonLikedByGeneration(region: String) = getAllPokemonsLiked().flatMap { list ->
        val pokemons = mutableListOf<Pokemon>()
        if (list.isNotEmpty()) {
            list.map { p ->
                if (p.generationName.equals(region, ignoreCase = true)) {
                    pokemons.add(p)
                }
            }
        }
        flowOf(pokemons)
    }

    override fun getPokemonLikedByType(type: String) = getAllPokemonsLiked().flatMap { list ->
        val pokemons = mutableListOf<Pokemon>()
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
package com.pokeapp.data_local.datasource.pokemon

import com.pokeapp.data.datasource.local.PokemonLocalDataSource
import com.pokeapp.data_local.dao.PokemonDAO
import com.pokeapp.data_local.mapper.PokemonLocalMapper
import com.pokeapp.data_local.model.PokemonLocal
import com.pokeapp.domain.model.Pokemon

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class PokemonLocalDataSourceImpl(private val dao: PokemonDAO) : PokemonLocalDataSource {

    override suspend fun insert(pokemon: Pokemon) {
        dao.insert(PokemonLocalMapper.toSaveLocal(pokemon))
    }

    override suspend fun delete(pokemon: Pokemon) {
        dao.delete(PokemonLocalMapper.toSaveLocal(pokemon))
    }

    override suspend fun getById(id: Int) =
        PokemonLocalMapper.toDomain(dao.getPokemon(id) ?: PokemonLocal())

    override suspend fun getAll() = PokemonLocalMapper.toDomainList(dao.getPokemons())

    override suspend fun getPokemonByGeneration(region: String): MutableList<Pokemon> {
        val pokemonsLocal = getAll()
        val pokemons = mutableListOf<Pokemon>()

        if (pokemonsLocal.isNotEmpty()) {
            pokemonsLocal.map { p ->
                if (p.generation.equals(region, ignoreCase = true)) {
                    pokemons.add(p)
                }
            }
        }

        return pokemons
    }

    override suspend fun getPokemonByType(type: String): MutableList<Pokemon> {
        val pokemonsLocal = getAll()
        val pokemons = mutableListOf<Pokemon>()

        if (pokemonsLocal.isNotEmpty()) {
            pokemonsLocal.map { p ->
                p.types.map { t ->
                    if (t.name.equals(type, ignoreCase = true)) {
                        pokemons.add(p)
                    }
                }
            }
        }

        return pokemons
    }
}
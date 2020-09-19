package com.pokeapp.data.cache.room.repository.pokemon

import com.pokeapp.PokeApplication
import com.pokeapp.data.cache.entities.PokemonLocal
import java.util.*

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class PokemonRoomImpl : PokemonRoom {

    override fun insert(pokemonLocal: PokemonLocal): Boolean {
        if (PokeApplication.database == null) return false

        PokeApplication.database?.apply {
            pokemonDao().insert(pokemonLocal)
        }
        Result
        return true
    }

    override fun delete(pokemonLocal: PokemonLocal): Boolean {
        if (PokeApplication.database == null) return false

        PokeApplication.database?.apply {
            pokemonDao().delete(pokemonLocal)
        }
        Result
        return true
    }

    override fun getById(id: Int) = PokeApplication.database?.pokemonDao()?.getPokemon(id)

    override fun getAll() = PokeApplication.database?.pokemonDao()?.getPokemons()

    override fun getPokemonByGeneration(region: String): MutableList<PokemonLocal>? {
        val pokemonsLocal = getAll()
        val pokemons = mutableListOf<PokemonLocal>()

        if (pokemonsLocal != null) {
            if (pokemonsLocal.isNotEmpty()) {
                pokemonsLocal.forEach { p ->
                    if (p.generation.toLowerCase(Locale("pt", "BR")) == region.toLowerCase(Locale("pt", "BR"))) {
                        pokemons.add(p)
                    }
                }
            }
        }

        return pokemons
    }

    override fun getPokemonByType(type: String): MutableList<PokemonLocal>? {
        val pokemonsLocal = getAll()
        val pokemons = mutableListOf<PokemonLocal>()

        if (pokemonsLocal != null) {
            if (pokemonsLocal.isNotEmpty()) {
                pokemonsLocal.forEach { p ->
                    p.types.forEach { t ->
                        if (t.name.toLowerCase(Locale("pt", "BR")) == type.toLowerCase(Locale("pt", "BR"))) {
                            pokemons.add(p)
                        }
                    }
                }
            }
        }

        return pokemons
    }
}
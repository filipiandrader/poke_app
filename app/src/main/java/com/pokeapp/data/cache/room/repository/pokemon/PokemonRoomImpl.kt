package com.pokeapp.data.cache.room.repository.pokemon

import com.pokeapp.PokeApplication
import com.pokeapp.data.cache.entities.PokemonLocal

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
}
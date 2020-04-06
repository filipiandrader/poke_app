package com.pokeapp.data.cache.room.repository.pokemon

import com.pokeapp.data.cache.entities.PokemonLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */
interface PokemonRoom {

    fun insert(pokemonLocal: PokemonLocal): Boolean

    fun delete(pokemonLocal: PokemonLocal): Boolean

    fun getById(id: Int): PokemonLocal?

    fun getAll(): MutableList<PokemonLocal>?
}
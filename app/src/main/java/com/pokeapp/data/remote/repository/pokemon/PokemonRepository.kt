package com.pokeapp.data.remote.repository.pokemon

import com.pokeapp.data.ResultRequest
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonRepository {

    suspend fun getAllPokemon(offset: Int) : ResultRequest<MutableList<Pokemon>>

    suspend fun getPokemonByGeneration(id: Int) : ResultRequest<MutableList<Pokemon>>
}
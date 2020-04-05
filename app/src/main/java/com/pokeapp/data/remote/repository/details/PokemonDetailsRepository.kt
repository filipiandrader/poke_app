package com.pokeapp.data.remote.repository.details

import com.pokeapp.data.ResultRequest
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade on 04/04/2020
 */
interface PokemonDetailsRepository {

    suspend fun getPokemonInfo(id: Int): ResultRequest<Pokemon>
}
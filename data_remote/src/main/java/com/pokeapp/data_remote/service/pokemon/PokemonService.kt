package com.pokeapp.data_remote.service.pokemon

import com.pokeapp.data_remote.model.pokedex.PokedexResponse
import com.pokeapp.data_remote.model.pokemon.PokemonInfoResponse
import com.pokeapp.data_remote.model.pokemon.PokemonResponse
import com.pokeapp.data_remote.utils.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonService {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("previous") previous: Int
    ): GenericResponse<PokedexResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): GenericResponse<PokemonInfoResponse>
}
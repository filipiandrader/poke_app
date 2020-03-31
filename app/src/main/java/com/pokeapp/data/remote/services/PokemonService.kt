package com.pokeapp.data.remote.services

import com.pokeapp.data.remote.model.PokemonResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonService {

    @GET("pokemon")
    fun getAllPokemon(@Query("offset") offset: Int,
                      @Query("limit") limit: Int = 20) : Deferred<Response<PokemonResponse>>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String) : Deferred<Response<HashMap<String, Any>>>

    @GET("evolution-chain/{id}")
    fun getPokemonEvolutionChain(@Path("id") id: Int) : Deferred<Response<HashMap<String, Any>>>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int) : Deferred<Response<HashMap<String, Any>>>
}
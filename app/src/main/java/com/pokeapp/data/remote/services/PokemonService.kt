package com.pokeapp.data.remote.services

import com.pokeapp.data.remote.model.GenerationResponse
import com.pokeapp.data.remote.model.PokemonResponse
import com.pokeapp.data.remote.model.RegionResponse
import com.pokeapp.data.remote.model.TypeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Filipi Andrade on 29/03/2020
 */
interface PokemonService {

    // POKEMON
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int,
                              @Query("limit") limit: Int = 20): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<HashMap<String, Any>>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Response<HashMap<String, Any>>

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChain(@Path("id") id: Int): Response<HashMap<String, Any>>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): Response<HashMap<String, Any>>

    // REGIÃO
    @GET("region")
    suspend fun getRegion(): Response<RegionResponse>

    @GET("region/{name}")
    suspend fun getRegionByName(@Path("name") name: String): Response<HashMap<String, Any>>

    /*@GET("pokedex/{name}")
    fun getPokedexByRegion(@Path("name") name: String): Deferred<Response<HashMap<String, Any>>>*/

    // GENERATION
    @GET("generation/{id}")
    suspend fun getPokemomByGeneration(@Path("id") id: Int): Response<GenerationResponse>

    // TIPOS
    @GET("type")
    suspend fun getType(): Response<TypeResponse>

    @GET("type/{id}")
    suspend fun getPokemonByType(@Path("id") id: Int): Response<HashMap<String, Any>>
}
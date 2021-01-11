package com.pokeapp.data_remote.service

import com.pokeapp.data_remote.model.*
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
                              @Query("limit") limit: Int = 20): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): HashMap<String, Any>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonInfoResponse

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChain(@Path("id") id: Int): HashMap<String, Any>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): HashMap<String, Any>

    // REGIÃO
    @GET("region")
    suspend fun getRegion(): RegionResponse

    @GET("region/{name}")
    suspend fun getRegionByName(@Path("name") name: String): HashMap<String, Any>

    /*@GET("pokedex/{name}")
    fun getPokedexByRegion(@Path("name") name: String): Deferred<Response<HashMap<String, Any>>>*/

    // GENERATION
    @GET("generation/{id}")
    suspend fun getPokemomByGeneration(@Path("id") id: Int): GenerationResponse

    // TIPOS
    @GET("type")
    suspend fun getType(): TypeResponse

    @GET("type/{id}")
    suspend fun getPokemonByType(@Path("id") id: Int): HashMap<String, Any>
}
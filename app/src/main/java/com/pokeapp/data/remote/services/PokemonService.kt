package com.pokeapp.data.remote.services

import com.pokeapp.data.remote.model.GenerationResponse
import com.pokeapp.data.remote.model.PokemonResponse
import com.pokeapp.data.remote.model.RegionResponse
import com.pokeapp.data.remote.model.TypeResponse
import kotlinx.coroutines.Deferred
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
    fun getAllPokemon(@Query("offset") offset: Int,
                      @Query("limit") limit: Int = 20): Deferred<Response<PokemonResponse>>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Deferred<Response<HashMap<String, Any>>>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Deferred<Response<HashMap<String, Any>>>

    @GET("evolution-chain/{id}")
    fun getPokemonEvolutionChain(@Path("id") id: Int): Deferred<Response<HashMap<String, Any>>>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Deferred<Response<HashMap<String, Any>>>

    // REGIÃO
    @GET("region")
    fun getRegion(): Deferred<Response<RegionResponse>>

    @GET("region/{name}")
    fun getRegionByName(@Path("name") name: String): Deferred<Response<HashMap<String, Any>>>

    @GET("pokedex/{name}")
    fun getPokedexByRegion(@Path("name") name: String): Deferred<Response<HashMap<String, Any>>>

    // GENERATION
    @GET("generation/{id}")
    fun getPokemomByGeneration(@Path("id") id: Int): Deferred<Response<GenerationResponse>>

    // TIPOS
    @GET("type")
    fun getType(): Deferred<Response<TypeResponse>>

    @GET("type/{id}")
    fun getPokemonByType(@Path("id") id: Int): Deferred<Response<HashMap<String, Any>>>
}
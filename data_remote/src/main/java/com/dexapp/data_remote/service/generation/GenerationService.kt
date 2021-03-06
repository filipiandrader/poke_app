package com.dexapp.data_remote.service.generation

import com.dexapp.data_remote.model.generation.GenerationResponse
import com.dexapp.data_remote.model.pokemon.PokemonResponse
import com.dexapp.data_remote.utils.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface GenerationService {

    @GET("generation")
    suspend fun getGeneration(): GenericResponse<List<GenerationResponse>>

    @GET("generation/{id}")
    suspend fun getPokemomByGeneration(@Path("id") id: Int): GenericResponse<List<PokemonResponse>>
}
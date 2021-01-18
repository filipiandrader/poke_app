package com.pokeapp.data_remote.service

import com.pokeapp.data_remote.model.PokemonResponse
import com.pokeapp.data_remote.utils.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface GenerationService {

    @GET("generationName/{id}")
    suspend fun getPokemomByGeneration(@Path("id") id: Int): GenericResponse<List<PokemonResponse>>
}
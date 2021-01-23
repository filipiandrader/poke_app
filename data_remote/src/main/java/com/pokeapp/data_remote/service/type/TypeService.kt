package com.pokeapp.data_remote.service.type

import com.pokeapp.data_remote.model.pokemon.PokemonResponse
import com.pokeapp.data_remote.model.type.TypeResponse
import com.pokeapp.data_remote.utils.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface TypeService {

    @GET("type")
    suspend fun getType(): GenericResponse<List<TypeResponse>>

    @GET("type/{name}")
    suspend fun getPokemonByType(@Path("name") name: String): GenericResponse<List<PokemonResponse>>
}
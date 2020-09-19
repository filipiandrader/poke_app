package com.pokeapp.data.remote.repository.favourite

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.remote.model.TypeResponse
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.util.verifyResponseResult

/**
 * Created by Filipi Andrade on 07/04/2020
 */

class FavouriteRepositoryImpl(private val api: PokemonService) : FavouriteRepository {

    override suspend fun getAllTypes(): ResultRequest<TypeResponse> {
        val response = api.getType()

        if (!response.verifyResponseResult()) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        return ResultRequest.success(response.body()!!)
    }
}
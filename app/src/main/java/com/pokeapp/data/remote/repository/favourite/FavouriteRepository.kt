package com.pokeapp.data.remote.repository.favourite

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.remote.model.TypeResponse

/**
 * Created by Filipi Andrade on 07/04/2020
 */
interface FavouriteRepository {

    suspend fun getAllTypes(): ResultRequest<TypeResponse>
}
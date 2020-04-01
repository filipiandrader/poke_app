package com.pokeapp.data.remote.repository.region

import com.pokeapp.data.ResultRequest
import com.pokeapp.presentation.model.Region

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionRepository {

    suspend fun getRegion(): ResultRequest<MutableList<Region>>
}
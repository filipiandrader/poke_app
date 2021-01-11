package com.pokeapp.data.datasource.remote

import com.pokeapp.domain.model.Region
import kotlinx.coroutines.flow.Flow

/**
 * Created by Filipi Andrade on 01/04/2020
 */

interface RegionDataSource {

    fun getRegion(): Flow<List<Region>>
}
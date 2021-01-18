package com.pokeapp.data_remote.service

import com.pokeapp.data_remote.model.RegionInfoResponse
import com.pokeapp.data_remote.model.RegionResponse
import com.pokeapp.data_remote.utils.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

interface RegionService {

    @GET("region")
    suspend fun getRegion(): GenericResponse<List<RegionResponse>>

    @GET("region/{name}")
    suspend fun getRegionByName(@Path("name") name: String): GenericResponse<RegionInfoResponse>
}
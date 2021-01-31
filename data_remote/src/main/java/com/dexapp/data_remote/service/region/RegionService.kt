package com.dexapp.data_remote.service.region

import com.dexapp.data_remote.model.region.RegionInfoResponse
import com.dexapp.data_remote.model.region.RegionResponse
import com.dexapp.data_remote.utils.GenericResponse
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
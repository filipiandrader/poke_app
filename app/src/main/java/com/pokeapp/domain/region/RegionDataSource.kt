package com.pokeapp.domain.region

import com.pokeapp.presentation.model.Region

/**
 * Created by Filipi Andrade on 01/04/2020
 */
interface RegionDataSource {

    fun getRegion(onSuccess: (MutableList<Region>) -> Unit,
                  onFailure: (t: Throwable) -> Unit)

    fun cancelJob()
}
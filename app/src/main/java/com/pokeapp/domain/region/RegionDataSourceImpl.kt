package com.pokeapp.domain.region

import com.pokeapp.data.remote.repository.region.RegionRepository
import com.pokeapp.presentation.model.Region
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionDataSourceImpl(private val repository: RegionRepository) : RegionDataSource {

    private var job: Job = Job()

    override fun getRegion(onSuccess: (MutableList<Region>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getRegion()

            if (response.throwable != null || response.data == null) {
                response.throwable?.printStackTrace()
                onFailure(response.throwable as Throwable)
                return@launch
            }

            onSuccess(response.data)
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}
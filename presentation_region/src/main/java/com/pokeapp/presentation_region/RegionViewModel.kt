package com.pokeapp.presentation_region

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.region.RegionMapper
import com.pokeapp.base_presentation.model.region.RegionBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.interactor.region.GetRegion
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 01/04/2020
 */

class RegionViewModel : ViewModel(), KoinComponent {

    private val getRegion: GetRegion by useCase()

    private val _fetchRegionViewState by viewState<List<RegionBinding>>()
    val fetchRegionViewState = _fetchRegionViewState.asLiveData()

    fun getRegion() {
        _fetchRegionViewState.postLoading()
        getRegion(
            onSuccess = { _fetchRegionViewState.postSuccess(RegionMapper.listFromDomain(it)) },
            onError = { _fetchRegionViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _fetchRegionViewState.postNeutral()
    }
}
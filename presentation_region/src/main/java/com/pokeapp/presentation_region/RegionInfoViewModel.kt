package com.pokeapp.presentation_region

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.region.RegionInfoMapper
import com.pokeapp.base_presentation.model.region.RegionInfoBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.usecase.region.GetRegionInfo
import org.koin.core.KoinComponent

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class RegionInfoViewModel : ViewModel(), KoinComponent {

    private val getRegionInfo: GetRegionInfo by useCase()

    private val _fetchRegionInfoViewState by viewState<RegionInfoBinding>()
    val fetchRegionInfoViewState = _fetchRegionInfoViewState.asLiveData()

    fun getRegionInfo(name: String) {
        _fetchRegionInfoViewState.postLoading()
        getRegionInfo(
            params = GetRegionInfo.Params(name),
            onSuccess = { _fetchRegionInfoViewState.postSuccess(RegionInfoMapper.fromDomain(it)) },
            onError = { _fetchRegionInfoViewState.postError(it) }
        )
    }

    fun cleanValues() {
        _fetchRegionInfoViewState.postNeutral()
    }
}
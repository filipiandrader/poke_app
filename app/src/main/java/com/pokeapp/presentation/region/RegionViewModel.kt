package com.pokeapp.presentation.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokeapp.domain.region.RegionDataSource
import com.pokeapp.presentation.State
import com.pokeapp.presentation.ViewState
import com.pokeapp.presentation.model.Region

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionViewModel(private val dataSource: RegionDataSource) : ViewModel() {

    private var mState = MutableLiveData<ViewState<MutableList<Region>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
    }

    fun getRegion() {
        mState.postValue(ViewState.loading())
        dataSource.getRegion(
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure(it))
                }
        )
    }

    fun getState(): LiveData<ViewState<MutableList<Region>>> = mState

    override fun onCleared() {
        super.onCleared()
        dataSource.cancelJob()
    }
}
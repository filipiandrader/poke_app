package com.pokeapp.presentation.region

import androidx.lifecycle.ViewModel

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionViewModel : ViewModel() {

  /*  private var mState = MutableLiveData<ViewState<MutableList<RegionBinding>>>()

    init {
        mState.value = ViewState(data = null, state = State.LOADING)
    }*/

    fun getRegion() {
        /*mState.postValue(ViewState.loading())
        dataSource.getRegion(
                onSuccess = {
                    mState.postValue(ViewState.success(it))
                },
                onFailure = {
                    mState.postValue(ViewState.failure(it))
                }
        )*/
    }

//    fun getState(): LiveData<ViewState<MutableList<RegionBinding>>> = mState
}
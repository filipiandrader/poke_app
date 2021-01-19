package com.pokeapp.base_feature.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.pokeapp.base_presentation.core.ViewStateListener
import org.koin.core.KoinComponent

/*
 * Created by Filipi Andrade Rocha on 22/09/2020.
 */

abstract class BaseFragment : Fragment(), ViewStateListener, KoinComponent {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addObservers(viewLifecycleOwner)
    }

    open fun addObservers(owner: LifecycleOwner) {}

    open fun setupView() {}

    override fun onStateError(error: Throwable) {
    }

    override fun onStateLoading() {
    }

    override fun hideLoading() {
    }
}
package com.dexapp.base_feature.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.dexapp.base_feature.customview.dialog.LoadingDialog
import com.dexapp.base_feature.util.extensions.shortToast
import com.dexapp.base_presentation.core.ViewStateListener
import org.koin.core.KoinComponent

/*
 * Created by Filipi Andrade Rocha on 22/09/2020.
 */

abstract class BaseFragment : Fragment(), ViewStateListener, KoinComponent {

    private val mLoadingDialog = LoadingDialog()

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
        hideLoading()
        error.message?.let {
            showDialog(it)
        }
    }

    fun showDialog(message: String, isAttachToActivity: Boolean = false) {
        // TODO CUSTOM DIALOG
        shortToast(message)
    }

    override fun onStateLoading() {
        hideLoading()
        childFragmentManager.let { mLoadingDialog.show(this) }
    }

    override fun hideLoading() {
        mLoadingDialog.dismissAllowingStateLoss()
    }
}
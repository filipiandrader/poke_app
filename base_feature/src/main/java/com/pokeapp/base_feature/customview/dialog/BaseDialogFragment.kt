package com.pokeapp.base_feature.customview.dialog

import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.pokeapp.base_feature.core.BaseFragment

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

open class BaseDialogFragment : DialogFragment(), LifecycleObserver {

    private var isLoading = false
    private var lifecycleOwner: LifecycleOwner? = null

    fun show(manager: FragmentManager) {
        show(manager, this::class.java.simpleName)
    }

    fun show(fragmentContainer: BaseFragment) {
        this.lifecycleOwner = fragmentContainer.apply {
            lifecycle.addObserver(this@BaseDialogFragment)
            show(
                fragmentContainer.childFragmentManager,
                this@BaseDialogFragment::class.java.simpleName
            )
        }
    }

    fun show(container: FragmentActivity) {
        this.lifecycleOwner = container.apply {
            lifecycle.addObserver(this@BaseDialogFragment)
            show(container.supportFragmentManager, this@BaseDialogFragment::class.java.simpleName)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun reset() {
        dismissAllowingStateLoss()
        this.lifecycleOwner?.lifecycle?.removeObserver(this)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded || isLoading) {
            isLoading = true
            try {
                super.show(manager, tag)
            } catch (e: Exception) {
                Unit
            }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isLoading) {
            isLoading = false
            super.dismissAllowingStateLoss()
        }
    }

    fun updateWindowFeature() {
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}
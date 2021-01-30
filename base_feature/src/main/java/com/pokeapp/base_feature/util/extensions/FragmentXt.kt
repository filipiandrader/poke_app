package com.pokeapp.base_feature.util.extensions

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun Fragment.addOnBackPressedCallback(owner: LifecycleOwner, onBackPressed: () -> Unit) {
    (requireActivity() as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(
        owner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    )
}

fun Fragment.changeStatusBarColor(color: Int) {
    requireActivity().window?.statusBarColor = color
}

fun Fragment.shortToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.getDrawableRes(drawable: Int) = ContextCompat.getDrawable(requireContext(), drawable)
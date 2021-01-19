package com.pokeapp.base_feature.util.extensions

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
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
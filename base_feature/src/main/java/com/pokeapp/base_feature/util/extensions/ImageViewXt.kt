package com.pokeapp.base_feature.util.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.*

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

fun ImageView.loadUrl(
        url: String?,
        @DrawableRes resPlaceholder: Int? = null,
        onFailure: (Exception?) -> Unit = {},
        onSuccess: () -> Unit = {},
        cleanCache: Boolean = false
) {
    if (url.isNullOrBlank()) return
    Picasso.get()
            .apply {
                if (cleanCache) invalidate(url)
            }
            .load(url)
            .apply {
                if (cleanCache) {
                    memoryPolicy(MemoryPolicy.NO_CACHE)
                    networkPolicy(NetworkPolicy.NO_CACHE)
                }
                resPlaceholder?.let { placeholder(it) }
            }
            .centerCrop()
            .fit()
            .into(this, getPicassoCallback(onSuccess, onFailure))
}

private fun getPicassoCallback(
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
): Callback {
    return object : Callback {
        override fun onSuccess() {
            onSuccess()
        }

        @Suppress("ConstantConditionIf")
        override fun onError(e: Exception?) {
            if (BuildConfig.DEBUG) e?.printStackTrace()
            onFailure(e)
        }
    }
}
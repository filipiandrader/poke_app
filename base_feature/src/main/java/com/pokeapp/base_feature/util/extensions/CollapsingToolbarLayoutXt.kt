package com.pokeapp.base_feature.util.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
import com.google.android.material.appbar.CollapsingToolbarLayout

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

fun CollapsingToolbarLayout.setColorFilter(color: Int) {
    this.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

fun CollapsingToolbarLayout.configureNoScroll() {
    val params = this.layoutParams as AppBarLayout.LayoutParams
    params.scrollFlags = SCROLL_FLAG_NO_SCROLL
    this.layoutParams = params
}

fun CollapsingToolbarLayout.configureScroll() {
    val params = this.layoutParams as AppBarLayout.LayoutParams
    params.scrollFlags = getScrollFlags()
    this.layoutParams = params
}

private fun getScrollFlags() = SCROLL_FLAG_SCROLL and SCROLL_FLAG_ENTER_ALWAYS and SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
package com.pokeapp.base_feature.util.extensions

import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

fun RecyclerView.enableScroll(enable: Boolean) {
    this.isNestedScrollingEnabled = enable
}

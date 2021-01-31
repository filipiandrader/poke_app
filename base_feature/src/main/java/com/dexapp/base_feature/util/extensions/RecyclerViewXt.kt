package com.dexapp.base_feature.util.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

fun RecyclerView.enableScroll(enable: Boolean) {
    this.isNestedScrollingEnabled = enable
}

fun RecyclerView.findFirstVisibleItemPosition() =
    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

fun RecyclerView.childCount() = layoutManager!!.childCount

fun RecyclerView.itemCount() = layoutManager!!.itemCount

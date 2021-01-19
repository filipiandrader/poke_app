package com.pokeapp.base_feature.util.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun SwipeRefreshLayout.setRefresh(refresh: Boolean) {
    this.isRefreshing = refresh
}
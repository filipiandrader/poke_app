package com.dexapp.base_feature.customview.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.dexapp.base_feature.R

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

open class BaseFullScreenDialog : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.PokeApp_Fullscreen_Dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogStyle()
    }

    private fun setupDialogStyle() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}
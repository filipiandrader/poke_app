package com.pokeapp.base_feature.customview.bottomsheet.base

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.LayoutMode.WRAP_CONTENT
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.pokeapp.base_feature.R

/*
 * Created by Filipi Andrade Rocha on 25/01/2021.
 */

open class BaseBottomSheet(private val fragment: Fragment) {

    var materialDialog: MaterialDialog

    init {
        materialDialog = MaterialDialog(fragment.requireActivity(), BottomSheet(WRAP_CONTENT))
            .setPeekHeight(literal = getPeekHeight().toInt())
            .customView(
                viewRes = R.layout.bottom_sheet_layout,
                scrollable = false,
                noVerticalPadding = true,
                horizontalPadding = false,
                dialogWrapContent = true
            )
    }

    fun show() {
        materialDialog.show()
    }

    fun getCustomView() = materialDialog.getCustomView()

    private fun getPeekHeight(): Double {
        val wm = fragment.requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y * 0.70
    }
}
package com.dexapp.base_feature.customview.bottomsheet.type

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexapp.base_feature.R
import com.dexapp.base_feature.customview.bottomsheet.base.BaseBottomSheet
import com.dexapp.base_feature.util.extensions.putText
import com.dexapp.base_presentation.model.type.TypeBinding

/*
 * Created by Filipi Andrade Rocha on 25/01/2021.
 */

class TypeBottomSheet(
    private val fragment: Fragment,
    private val type: List<TypeBinding>
) : BaseBottomSheet(fragment) {

    private lateinit var typeNameTextView: TextView
    private lateinit var typeRecyclerView: RecyclerView
    private lateinit var typeAdapter: TypeAdapter

    fun show(clickListener: (type: TypeBinding) -> Unit) {
        typeNameTextView = getCustomView().findViewById(R.id.itemFilterNameTextView)
        typeNameTextView.putText(fragment.requireContext().getString(R.string.bottom_sheet_type_label))

        typeRecyclerView = getCustomView().findViewById(R.id.bottomSheetRecyclerView)
        typeAdapter = TypeAdapter {
            materialDialog.dismiss()
            clickListener(it)
        }
        typeAdapter.items = type.toMutableList()
        typeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = typeAdapter
        }
        show()
    }
}
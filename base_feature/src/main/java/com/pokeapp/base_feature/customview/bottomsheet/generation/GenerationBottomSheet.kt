package com.pokeapp.base_feature.customview.bottomsheet.generation

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.base_feature.R
import com.pokeapp.base_feature.customview.bottomsheet.base.BaseBottomSheet
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.generation.GenerationBinding

/*
 * Created by Filipi Andrade Rocha on 25/01/2021.
 */

class GenerationBottomSheet(
    private val fragment: Fragment,
    private val generation: List<GenerationBinding>
) : BaseBottomSheet(fragment) {

    private lateinit var generationNameTextView: TextView
    private lateinit var generationRecyclerView: RecyclerView
    private lateinit var generationAdapter: GenerationAdapter

    fun show(clickListener: (generation: GenerationBinding) -> Unit) {
        generationNameTextView = getCustomView().findViewById(R.id.itemFilterNameTextView)
        generationNameTextView.putText(
            fragment.requireContext().getString(R.string.bottom_sheet_generation_label)
        )

        generationRecyclerView = getCustomView().findViewById(R.id.bottomSheetRecyclerView)

        generationAdapter = GenerationAdapter {
            materialDialog.dismiss()
            clickListener(it)
        }
        generationAdapter.items = generation.toMutableList()
        generationRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = generationAdapter
        }
        show()
    }
}
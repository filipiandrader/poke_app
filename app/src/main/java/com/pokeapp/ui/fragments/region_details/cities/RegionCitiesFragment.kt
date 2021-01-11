package com.pokeapp.ui.fragments.region_details.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem

import com.pokeapp.R
import com.pokeapp.presentation.model.Location
import com.pokeapp.presentation.model.Region
import com.pokeapp.ui.fragments.region_details.RegionDetailsViewHolder
import com.pokeapp.base_feature.util.extensions.formatNameRegion
import com.pokeapp.base_feature.util.extensions.putText
import kotlinx.android.synthetic.main.fragment_region_cities.*

/**
 * A simple [Fragment] subclass.
 */
class RegionCitiesFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(region: Region) = RegionCitiesFragment().apply {
            arguments = Bundle().apply {
                putSerializable("region", region)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val region = checkNotNull(arguments?.getSerializable("region") as Region)
        regionCitiesRecyclerView.setup {
            withLayoutManager(GridLayoutManager(view.context, 2))
            withDataSource(dataSourceOf(region.locations))
            withItem<Location, RegionDetailsViewHolder>(R.layout.item_region_details) {
                onBind(::RegionDetailsViewHolder) { _, item ->
                    this.itemNameTextView.putText(item.name.formatNameRegion())
                }
            }
        }
    }
}

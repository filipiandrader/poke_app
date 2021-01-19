package com.pokeapp.feature_region.fragment.info.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_presentation.model.RegionBinding
import com.pokeapp.feature_region.R

class RegionCitiesFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(region: RegionBinding) = RegionCitiesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("region", region)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_cities, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val region = checkNotNull(arguments?.getSerializable("region") as RegionBinding)
        regionCitiesRecyclerView.setup {
            withLayoutManager(GridLayoutManager(view.context, 2))
            withDataSource(dataSourceOf(region.locations))
            withItem<LocationBinding, RegionDetailsViewHolder>(R.layout.item_region_details) {
                onBind(::RegionDetailsViewHolder) { _, item ->
                    this.itemNameTextView.putText(item.name.formatNameRegion())
                }
            }
        }
    }*/
}

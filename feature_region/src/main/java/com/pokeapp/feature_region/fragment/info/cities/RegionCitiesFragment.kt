package com.pokeapp.feature_region.fragment.info.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_presentation.model.region.RegionInfoBinding
import com.pokeapp.feature_region.adapter.LocationAdapter
import com.pokeapp.feature_region.databinding.FragmentRegionCitiesBinding

class RegionCitiesFragment : BaseFragment() {

    private lateinit var binding: FragmentRegionCitiesBinding
    private val locationAdapter = LocationAdapter()

    companion object {
        @JvmStatic
        fun newInstance(region: RegionInfoBinding) = RegionCitiesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("region", region)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegionCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        val region = checkNotNull(requireArguments().getParcelable("region")) as RegionInfoBinding
        binding.run {
            locationAdapter.items = region.locations.toMutableList()
            regionCitiesRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = locationAdapter
            }
        }
    }
}

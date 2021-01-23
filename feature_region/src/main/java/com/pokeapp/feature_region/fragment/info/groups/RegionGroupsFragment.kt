package com.pokeapp.feature_region.fragment.info.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_presentation.model.region.RegionInfoBinding
import com.pokeapp.feature_region.adapter.GroupAdapter
import com.pokeapp.feature_region.databinding.FragmentRegionGroupsBinding


class RegionGroupsFragment : BaseFragment() {

    private lateinit var binding: FragmentRegionGroupsBinding
    private val groupAdapter = GroupAdapter()

    companion object {
        @JvmStatic
        fun newInstance(region: RegionInfoBinding) = RegionGroupsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("region", region)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegionGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        val region = checkNotNull(requireArguments().getParcelable("region")) as RegionInfoBinding
        binding.run {
            groupAdapter.items = region.groups.toMutableList()
            regionGroupsRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = groupAdapter
            }
        }
    }
}

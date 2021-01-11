package com.pokeapp.ui.fragments.region

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.google.android.material.appbar.AppBarLayout
import org.koin.android.viewmodel.ext.android.viewModel
import com.pokeapp.R
import com.pokeapp.databinding.FragmentRegionBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.model.Region
import com.pokeapp.presentation.region.RegionViewModel
import com.pokeapp.base_feature.util.extensions.PokemonColorUtil
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_feature.util.extensions.setVisible
import kotlinx.android.synthetic.main.fragment_region.*

/**
 * A simple [Fragment] subclass.
 */
class RegionFragment : Fragment() {

    private val mViewModel: RegionViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRegionBinding = FragmentRegionBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        creatingObservers()
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.background)
    }

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }

        mViewModel.getRegion()
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    regionProgressBar.setVisible(true)
                    regionRecyclerView.setVisible(false)
                }
                State.SUCCESS -> {
                    regionMessageTextView.setVisible(false)
                    regionProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { region ->
                        setupRecyclerView(region)
                    }
                }
                State.FAILURE -> {
                    viewState.throwable?.message?.let {
                        regionMessageTextView.setVisible(true)
                        regionRecyclerView.setVisible(false)
                        regionProgressBar.setVisible(false)
                        regionMessageTextView.putText(it)
                    }
                }
                else -> { /* ignore */
                }
            }
        })
    }

    @SuppressLint("DefaultLocale")
    private fun setupRecyclerView(region: MutableList<Region>) {
        if (region.isNotEmpty()) {
            regionRecyclerView.setup {
                withLayoutManager(GridLayoutManager(context, 2))
                withDataSource(dataSourceOf(region))
                withItem<Region, RegionViewHolder>(R.layout.item_region) {
                    onBind(::RegionViewHolder) { _, item ->
                        this.itemRegionNameTextView.putText(item.name.capitalize())

                        val color = PokemonColorUtil(itemView.context).getCardViewColor(item.name)
                        this.itemRegionCardView.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                    }

                    onClick { index ->
                        val bundle = bundleOf("region" to region[index])
                        findNavController().navigate(R.id.action_regionFragment_to_regionDetailsFragment, bundle)
                    }
                }
            }

            if (region.size > 12) {
                val params: AppBarLayout.LayoutParams = regionCollapsingToolbarLayout.layoutParams as AppBarLayout.LayoutParams
                params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
                regionCollapsingToolbarLayout.layoutParams = params
                regionRecyclerView.isNestedScrollingEnabled = false
            } else {
                val params: AppBarLayout.LayoutParams = regionCollapsingToolbarLayout.layoutParams as AppBarLayout.LayoutParams
                params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL and AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS and AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
                regionCollapsingToolbarLayout.layoutParams = params
                regionRecyclerView.isNestedScrollingEnabled = true
            }
        } else {
            regionMessageTextView.setVisible(true)
            regionMessageTextView.putText("Sem regiões cadastradas :(")
        }
        regionRecyclerView.setVisible(region.isNotEmpty())
    }

}

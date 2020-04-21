package com.pokeapp.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pokeapp.R
import com.pokeapp.presentation.model.Region
import com.pokeapp.ui.fragments.region_details.cities.RegionCitiesFragment
import com.pokeapp.ui.fragments.region_details.groups.RegionGroupsFragment

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class RegionDetailsViewPagerAdapter(supportFragmentManager: FragmentManager, context: Context, private val region: Region) :
        FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
            Page(context.getString(R.string.region_details_tab_1), { RegionCitiesFragment.newInstance(region) }),
            Page(context.getString(R.string.region_details_tab_2), { RegionGroupsFragment.newInstance(region) })
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }

}
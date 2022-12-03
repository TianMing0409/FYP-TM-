package com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.R.*
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CommunityDashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(layout.fragment_community_dashboard,container,false)

        val adapter = CommunityDashboardPagerAdapter(requireActivity())
        val viewPager : ViewPager2 = view.findViewById(R.id.viewpagerComm)
        viewPager.adapter = adapter

        val tabs : TabLayout = view.findViewById(R.id.tabLayoutComm)
        TabLayoutMediator(tabs,viewPager, CommunityDashboardFragment.TabConfigurationComm()).attach()

        return view
    }

    class TabConfigurationComm: TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            val tabNames = listOf("Community", "My Activity","My Bookmark")
            tab.setText(tabNames[position])
        }
    }

}
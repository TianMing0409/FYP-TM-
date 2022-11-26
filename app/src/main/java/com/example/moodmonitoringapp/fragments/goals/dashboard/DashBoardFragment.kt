package com.example.moodmonitoringapp.fragments.goals.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentDashboardBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.CommunityFragment
import com.example.moodmonitoringapp.fragments.goals.AddGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.GoalsEditFragment
import com.example.moodmonitoringapp.util.viewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator


class DashBoardFragment : Fragment() {

    private lateinit var binding : FragmentDashboardBinding
    private lateinit var addGoalFab: FloatingActionButton
    private val addGoalsFragment = AddGoalsFragment()

//
////    private lateinit var viewPagerAdapter :ViewPagerAdapter
//private val myContext = FragmentActivity()
//
    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {

    val view =inflater.inflate(R.layout.fragment_dashboard,container,false)

    addGoalFab = view.findViewById(R.id.fabAddGoal)

    addGoalFab.setOnClickListener{v:View->
        val activity=v!!.context as AppCompatActivity
        val fragmentAddGoals =AddGoalsFragment()
        activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentAddGoals).addToBackStack(null).commit()
    }

    return view

    }




//    private lateinit var viewPagerAdapter: DashboardPagerAdapter
//    private val binding by viewBinding(FragmentGoalsBinding::bind)
//    //private lateinit var binding : FragmentGoalsBinding
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewPagerAdapter = DashboardPagerAdapter(requireActivity())
//
//        initViewPager()
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    binding = FragmentGoalsBinding.bind(view)
//
//    val sectionPagerAdapter = ViewPagerAdapter(this)
//    val viewPager: ViewPager2 = binding.viewpager
//    viewPager.adapter = sectionPagerAdapter
//    val tabs: TabLayout = binding.tabLayout
//    TabLayoutMediator(tabs, viewPager) { tab, position ->
//        tab.text = resources.getString(TAB_TITLES[position])
//    }.attach()
//}

//    private fun initViewPager() {
//        binding.viewpager.adapter = viewPagerAdapter
//        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
//            when (position) {
//                0 -> {
//                    tab.text = "Active"
//                }
//                1 -> {
//                    tab.text = "Completed"
//                }
//            }
//        }.attach()
//    }


//companion object {
//    @StringRes
//    private val TAB_TITLES = intArrayOf(
//        R.string.tab1,
//        R.string.tab2
//    )
//}
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        setUpTabs()
//    }
//
//    private fun setUpTabs(){
//        val adapter = ViewPagerAdapter(parentFragmentManager)
//        adapter.addFragment(GoalsFragment(), "Active")
//        adapter.addFragment(CompletedGoalsFragment(),"Completed")
//        binding.viewPager.adapter = adapter
//        binding.tabLayout.setupWithViewPager(binding.viewPager)
//
//
//
//    }

}
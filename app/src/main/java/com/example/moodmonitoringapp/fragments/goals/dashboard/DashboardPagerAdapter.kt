package com.example.moodmonitoringapp.fragments.goals.dashboard

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.CompletedGoalsFragment


//class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    var fragmentList : ArrayList<Fragment> = ArrayList()
//    var fragmentTitle : ArrayList<String> = ArrayList()
//
//    override fun getCount(): Int {
//        return fragmentList.size
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return fragmentList[position]
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return fragmentTitle[position]
//    }
//
//    fun addFragment(fragment: Fragment, title: String){
//        fragmentList.add(fragment)
//        fragmentTitle.add(title)
//    }
//
//}

class DashboardPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActiveGoalsFragment.newInstance()
            else -> CompletedGoalsFragment.newInstance()
        }
    }
}


//    class ViewPagerAdapter(
//        fragment: Fragment,
//    ) : FragmentStateAdapter(fragment) {
//
//        override fun getItemCount(): Int {
//            return 2
//        }
//
//        override fun createFragment(position: Int): Fragment {
//            var fragment: Fragment? = null
//            when (position) {
//                0 -> fragment = GoalsFragment()
//                1 -> fragment = CompletedGoalsFragment()
//            }
//            return fragment as Fragment
//        }
//    }


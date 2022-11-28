package com.example.moodmonitoringapp.fragments.goals.dashboard

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.CompletedGoalsFragment

class DashboardPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ActiveGoalsFragment()
            1 -> return CompletedGoalsFragment()
        }
        return ActiveGoalsFragment()
    }
}

//class DashboardPagerAdapter(fm: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {
//        override fun getItemCount(): Int {
//        return 2
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        when(position){
//            0 -> return ActiveGoalsFragment()
//            1 -> return CompletedGoalsFragment()
//            else -> return ActiveGoalsFragment()
//        }
//    }
////    override fun getCount(): Int {
////        return 2
////    }
////
////    override fun getItem(position: Int): Fragment {
////        when(position){
////            0 -> return ActiveGoalsFragment()
////            1 -> return CompletedGoalsFragment()
////            else -> return ActiveGoalsFragment()
////        }
////    }
//
//
//}

//class DashboardPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
////        override fun getItemCount(): Int {
////        return 2
////    }
////
////    override fun createFragment(position: Int): Fragment {
////        when(position){
////            0 -> return ActiveGoalsFragment()
////            1 -> return CompletedGoalsFragment()
////            else -> return ActiveGoalsFragment()
////        }
////    }
//
//    override fun getCount(): Int {
//        return 2
//    }
//
//    override fun getItem(position: Int): Fragment {
//        when(position){
//            0 -> return ActiveGoalsFragment()
//            1 -> return CompletedGoalsFragment()
//            else -> return ActiveGoalsFragment()
//        }
//    }
////    override fun getCount(): Int {
////        return 2
////    }
////
////    override fun getItem(position: Int): Fragment {
////        when(position){
////            0 -> return ActiveGoalsFragment()
////            1 -> return CompletedGoalsFragment()
////            else -> return ActiveGoalsFragment()
////        }
////    }
//
//
//}


//Working pure dashboard without content (active,completed),
//class DashboardPagerAdapter(
//    fragmentActivity: FragmentActivity,
//) : FragmentStateAdapter(fragmentActivity) {
//
//    override fun getItemCount() = 2
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> ActiveGoalsFragment.newInstance()
//            else -> CompletedGoalsFragment.newInstance()
//        }
//    }
//}




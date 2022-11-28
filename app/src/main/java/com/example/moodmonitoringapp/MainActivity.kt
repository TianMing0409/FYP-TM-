package com.example.moodmonitoringapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moodmonitoringapp.databinding.ActivityMainBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.CommunityFragment
import com.example.moodmonitoringapp.fragments.communityPlatform.MyActivityFragment
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.CompletedGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.example.moodmonitoringapp.fragments.home.HomeFragment
import com.example.moodmonitoringapp.fragments.profile.ProfileFragment
import com.example.moodmonitoringapp.fragments.stats.StatsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    private val homeFragment = HomeFragment()
    private val statsFragment = StatsFragment()
    private val dashBoardFragment = DashBoardFragment()    //Currently not working, pending future works
    private val activeGoalsFragment = ActiveGoalsFragment()         //Testing purpose, need to remove
    private val completedGoalsFragment = CompletedGoalsFragment()   //Testing purpose, need to remove
    private val profileFragment = ProfileFragment()
    private val communityFragment = CommunityFragment()
    private val myActivityFragment = MyActivityFragment()           //Testing purpose, need to remove

    // FloatingActionButton for all the FABs
    private lateinit var addFab: FloatingActionButton
    private lateinit var communityFab: FloatingActionButton

    // These are taken to make visible and invisible along with FABs
    private lateinit var communityText: TextView

    // to check whether sub FAB buttons are visible or not.
    private var isAllFabsVisible: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.stats ->replaceFragment(statsFragment)
                R.id.goals -> replaceFragment(dashBoardFragment)   //Currently not working completely, pending future works
                //R.id.goals -> replaceFragment(activeGoalsFragment)   //Testing purpose, need to remove
                //R.id.goals -> replaceFragment(completedGoalsFragment)   //Testing purpose, need to remove
                R.id.profile -> replaceFragment(profileFragment)
            }
            true
        }

        // Register all the FABs with their IDs This FAB button is the Parent
        addFab = findViewById(R.id.add_fab)

        // FAB button
        communityFab = findViewById(R.id.community_fab)

        // Also register the action name text, of all the FABs.
        communityText = findViewById(R.id.community_text)

        // Now set all the FABs and all the action name texts as GONE
        communityFab.visibility = View.GONE
        communityText.visibility = View.GONE

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        addFab.setOnClickListener(View.OnClickListener {
            (if (!isAllFabsVisible!!) {
                // when isAllFabsVisible becomes true make all
                // the action name texts and FABs VISIBLE
                communityFab.show()
                communityText.visibility = View.VISIBLE

                // make the boolean variable true as we
                // have set the sub FABs visibility to GONE
                true
            } else {
                // when isAllFabsVisible becomes true make
                // all the action name texts and FABs GONE.
                communityFab.hide()
                communityText.visibility = View.GONE

                // make the boolean variable false as we
                // have set the sub FABs visibility to GONE
                false
            }).also { isAllFabsVisible = it }
        })

        // below is the sample action to handle add person FAB. Here it shows simple Toast msg.
        // The Toast will be shown only when they are visible and only when user clicks on them
        communityFab.setOnClickListener {
            replaceFragment(communityFragment)
            //replaceFragment(myActivityFragment)    //Testing purpose, need to remove
        }


    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null ){

            val fragmentTransaction  = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.commit()
        }
    }

}
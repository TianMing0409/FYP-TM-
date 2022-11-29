package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.GoalRecyclerAdapter
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.CommunityFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.Communicator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList


class ActiveGoalsFragment : Fragment(R.layout.fragment_active_goals), Communicator {

    private lateinit var binding : FragmentActiveGoalsBinding
    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList :ArrayList<Goals>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""


    //private val binding by viewBinding(FragmentActiveGoalsBinding::bind)

    //Testing purpose variables (Need to modify)
//    private var titleList = mutableListOf<String>()
//    private var dateList = mutableListOf<String>()
//    private var imageList = mutableListOf<Int>()


    companion object {
        fun newInstance() = CommunityFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActiveGoalsBinding.inflate(inflater,container,false)
        //val view =inflater.inflate(R.layout.fragment_active_goals,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Goals")


        userRecyclerView = binding.goalRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Goals>()

        getGoalsData()

        return binding.root
    }

    private fun getGoalsData(){

        val getData = db.child("Active").child(userUId)

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(goalSnapshot in snapshot.children){
                        val goals = goalSnapshot.getValue(Goals::class.java)
                        userArrayList.add(goals!!)
                    }
                    userRecyclerView.adapter = GoalRecyclerAdapter(userArrayList,this@ActiveGoalsFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun passData(position: Int, goalID : String, goal_title: String, goal_status: String, goal_target_date: String) {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_goal_id",goalID)
        bundle.putString("input_goal_title", goal_title)
        bundle.putString("input_goal_status",goal_status)
        bundle.putString("input_goal_target_date", goal_target_date)

        val transaction = this.parentFragmentManager.beginTransaction()
        val goalDetailsFragment = GoalsDetailsFragment()
        goalDetailsFragment.arguments = bundle

        transaction.replace(R.id.fragment_container, goalDetailsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
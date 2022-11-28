package com.example.moodmonitoringapp.fragments.goals

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentGoalsDetailsBinding
import com.example.moodmonitoringapp.fragments.goals.dashboard.Communicator
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GoalsDetailsFragment : Fragment() {

    private lateinit var binding : FragmentGoalsDetailsBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    var inputPos: Int? = null
    var inputGoalId: String = ""
    var inputGoalTitle: String = ""
    var inputGoalStatus: String = ""
    var inputGoalTargetDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_goals_details,container,false)
        binding = FragmentGoalsDetailsBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Goals")

        val btnComplete: Button = binding.completeBtn
        val btnEdit : Button = binding.editBtn


//        val goal_title: TextView = view.findViewById(R.id.goal_title)
//        val goal_target_date : TextView = view.findViewById(R.id.schedule_date)

        //fetch data
        inputPos = arguments?.getInt("input_pos")
        inputGoalId = arguments?.getString("input_goal_id").toString()
        inputGoalTitle = arguments?.getString("input_goal_title").toString()
        inputGoalStatus = arguments?.getString("input_goal_status").toString()
        inputGoalTargetDate = arguments?.getString("input_goal_target_date").toString()

        binding.goal?.text = inputGoalTitle
        binding.scheduleDate?.text = inputGoalTargetDate

        btnComplete.setOnClickListener(){

            uploadGoal(inputGoalId,inputGoalTitle,inputGoalStatus,inputGoalTargetDate)
            deleteGoal(inputGoalId,inputGoalTitle,inputGoalStatus,inputGoalTargetDate)

            Toast.makeText(context, "Congratulations!", Toast.LENGTH_SHORT).show()
        }

        btnEdit.setOnClickListener(){ v:View ->

            //Send data
            val bundle = Bundle()
            bundle.putString("ori_goal_id",inputGoalId)
            bundle.putString("ori_goal_title", inputGoalTitle)
            bundle.putString("ori_goal_status",inputGoalStatus)
            bundle.putString("ori_goal_target_date", inputGoalTargetDate)

            // Navigate fragment
            val transaction = this.parentFragmentManager.beginTransaction()
            val goalEditFragment = GoalsEditFragment()
            goalEditFragment.arguments = bundle

            transaction.replace(R.id.fragment_container, goalEditFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
        return binding.root
    }


    private fun deleteGoal(goalID : String ,goalName : String ,goalStatus : String, goalTargetDate : String) {

        db.child("Active").child(userUId)
            .child(goalID).removeValue()

        replaceFragment(DashBoardFragment())     // Need to change replace dashboard fragment
    }

    private fun uploadGoal(goalID : String ,goalName : String ,goalStatus : String, goalTargetDate : String) {

        val goal = Goals(goalID, goalName,goalStatus, goalTargetDate)

        db.child("Completed").child(userUId)
            .child(goalID).setValue(goal).addOnSuccessListener {

//                Toast.makeText(context, "Submit Successfully!", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null ){

            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.commit()
        }
    }

}
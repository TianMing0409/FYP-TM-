package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.databinding.FragmentGoalsDetailsBinding
import com.example.moodmonitoringapp.databinding.FragmentGoalsEditBinding
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GoalsEditFragment : Fragment() {

    private lateinit var binding : FragmentGoalsEditBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    var inputPos: Int? = null
    var inputGoalId : String = ""
    var inputGoalTitle: String = ""
    var inputGoalStatus: String = ""
    var inputGoalTargetDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_goals_edit,container,false)
        binding = FragmentGoalsEditBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Goals")

        val btnSave: Button = binding.saveBtn
        val btnDelete : Button = binding.delBtn

        //fetch data
        inputGoalId = arguments?.getString("ori_goal_id").toString()
        inputGoalTitle = arguments?.getString("ori_goal_title").toString()
        inputGoalStatus = arguments?.getString("ori_goal_status").toString()
        inputGoalTargetDate = arguments?.getString("ori_goal_target_date").toString()

        binding.inputNewGoal.setText(inputGoalTitle)
        binding.inputNewDate.setText(inputGoalTargetDate)

        btnSave.setOnClickListener(){
            validateData()
        }

        btnDelete.setOnClickListener(){
            deleteGoal(inputGoalId,inputGoalTitle,inputGoalStatus,inputGoalTargetDate)
        }

        return binding.root
    }

    private var goalID=""
    private var goalName=""
    private var goalTargetDate = ""
    private var goalStatus = ""

    private fun validateData(){
        goalID= inputGoalId
        goalName = binding.inputNewGoal.text.toString().trim()
        goalStatus = inputGoalStatus
        goalTargetDate = binding.inputNewDate.text.toString().trim()

        if(goalName.isEmpty()){
            Toast.makeText(context, "Enter goal...", Toast.LENGTH_SHORT).show()
        }
        else if(goalTargetDate.isEmpty()){
            Toast.makeText(context,"Enter target date...",Toast.LENGTH_SHORT).show()
        }
        else{
            //Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
            editGoal(goalID,goalName,goalStatus,goalTargetDate)
        }
    }

    private fun editGoal(goalID : String ,goalName : String ,goalStatus : String, goalTargetDate : String) {

        val goal = Goals(goalID, goalName,goalStatus, goalTargetDate)

        db.child("Active").child(userUId)
            .child(goalID).setValue(goal).addOnSuccessListener {
                binding.inputNewGoal.text.clear()
                binding.inputNewDate.text.clear()
                Toast.makeText(context, "Edit Successfully!", Toast.LENGTH_SHORT).show()

                replaceFragment(DashBoardFragment())   // Need to change replace dashboard fragment

            }.addOnFailureListener{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteGoal(goalID : String ,goalName : String ,goalStatus : String, goalTargetDate : String) {

        db.child("Active").child(userUId)
            .child(goalID).removeValue()

        Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show()

        replaceFragment(DashBoardFragment())     // Need to change replace dashboard fragment
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null ){

            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.commit()
        }
    }
}
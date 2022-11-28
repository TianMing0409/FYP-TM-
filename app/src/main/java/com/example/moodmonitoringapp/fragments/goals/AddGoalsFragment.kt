package com.example.moodmonitoringapp.fragments.goals

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentAddGoalsBinding
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class AddGoalsFragment : Fragment() {

    private lateinit var binding : FragmentAddGoalsBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_add_goals,container,false)
        binding = FragmentAddGoalsBinding.inflate(inflater,container,false)

        val btnSubmit: Button = binding.submitBtn

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Goals")

        btnSubmit.setOnClickListener(){
//            Toast.makeText(context, "Submit Successfully!", Toast.LENGTH_SHORT).show()
            validateData()
        }

        return binding.root

    }

//    private var goalID=""
    private var goalName=""
    private var goalTargetDate = ""
//    private var goalStatus = ""

    private fun validateData(){
//        goalID = "G" + (0..3000).random()
        goalName = binding.inputGoal.text.toString().trim()
        goalTargetDate = binding.inputDate.text.toString().trim()
//        goalStatus = "Active"

        if(goalName.isEmpty()){
            Toast.makeText(context, "Enter goal...", Toast.LENGTH_SHORT).show()
        }
        else if(goalTargetDate.isEmpty()){
            Toast.makeText(context,"Enter target date...",Toast.LENGTH_SHORT).show()
        }
        else{
            //Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
            uploadGoal(goalName,goalTargetDate)
            Toast.makeText(context, "Submit Successfully!", Toast.LENGTH_SHORT).show()

            replaceFragment(DashBoardFragment())   // Need to change replace dashboard fragment
        }
    }

    private fun uploadGoal(goalName : String , goalTargetDate : String) {

        val goalID = "G" + (0..3000).random()
        val goalStatus = "Active"
        val goal = Goals(goalID, goalName,goalStatus, goalTargetDate)

        db.child("Active").child(userUId)
            .child(goalID).setValue(goal).addOnSuccessListener {
                binding.inputGoal.text.clear()
                binding.inputDate.text.clear()

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
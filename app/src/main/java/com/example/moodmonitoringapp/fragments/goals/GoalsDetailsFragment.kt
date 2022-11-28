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
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentGoalsDetailsBinding
import com.example.moodmonitoringapp.fragments.goals.dashboard.Communicator

class GoalsDetailsFragment : Fragment() {

    private lateinit var binding : FragmentGoalsDetailsBinding

    var inputPos: Int? = null
    var inputGoalTitle: String = ""
    var inputGoalTargetDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_goals_details,container,false)
        binding = FragmentGoalsDetailsBinding.inflate(inflater,container,false)

        val btnComplete: Button = binding.completeBtn
        val btnEdit : Button = binding.editBtn


//        val goal_title: TextView = view.findViewById(R.id.goal_title)
//        val goal_target_date : TextView = view.findViewById(R.id.schedule_date)

        //fetch data
        inputPos = arguments?.getInt("input_pos")
        inputGoalTitle = arguments?.getString("input_goal_title").toString()
        inputGoalTargetDate = arguments?.getString("input_goal_target_date").toString()

        binding.goal?.text = inputGoalTitle
        binding.scheduleDate?.text = inputGoalTargetDate


        btnComplete.setOnClickListener(){
            Toast.makeText(context, "Congratulations!", Toast.LENGTH_SHORT).show()
        }

        btnEdit.setOnClickListener(){ v:View ->

            //Send data
            val bundle = Bundle()
            bundle.putString("ori_goal_title", inputGoalTitle)
            bundle.putString("ori_goal_target_date", inputGoalTargetDate)

            val transaction = this.parentFragmentManager.beginTransaction()
            val goalEditFragment = GoalsEditFragment()
            goalEditFragment.arguments = bundle

            transaction.replace(R.id.fragment_container, goalEditFragment)
            transaction.addToBackStack(null)
            transaction.commit()

//            val activity=v!!.context as AppCompatActivity
//            val fragmentEditGoals = GoalsEditFragment()
//            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentEditGoals).addToBackStack(null).commit()
        }



        return binding.root

    }


}
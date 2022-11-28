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
import com.example.moodmonitoringapp.databinding.FragmentGoalsDetailsBinding
import com.example.moodmonitoringapp.databinding.FragmentGoalsEditBinding

class GoalsEditFragment : Fragment() {

    private lateinit var binding : FragmentGoalsEditBinding

    var inputPos: Int? = null
    var inputGoalTitle: String = ""
    var inputGoalTargetDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_goals_edit,container,false)
        binding = FragmentGoalsEditBinding.inflate(inflater,container,false)

        val btnSave: Button = binding.saveBtn
        val btnDelete : Button = binding.delBtn

        //fetch data
        inputGoalTitle = arguments?.getString("ori_goal_title").toString()
        inputGoalTargetDate = arguments?.getString("ori_goal_target_date").toString()

        binding.inputNewGoal.setText(inputGoalTitle)
        binding.inputNewDate.setText(inputGoalTargetDate)

        btnSave.setOnClickListener(){
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener(){
            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}
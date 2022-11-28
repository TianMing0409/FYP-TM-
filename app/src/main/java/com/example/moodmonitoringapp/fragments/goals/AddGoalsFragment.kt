package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentAddGoalsBinding

class AddGoalsFragment : Fragment() {

    private lateinit var binding : FragmentAddGoalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view =inflater.inflate(R.layout.fragment_add_goals,container,false)
        binding = FragmentAddGoalsBinding.inflate(inflater,container,false)

        val btnSubmit: Button = binding.submitBtn

        btnSubmit.setOnClickListener(){
            Toast.makeText(context, "Submit Successfully!", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

}
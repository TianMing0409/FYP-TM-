package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moodmonitoringapp.R

class GoalsDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =inflater.inflate(R.layout.fragment_goals_details,container,false)

        val btnComplete: Button =view.findViewById(R.id.completeBtn)
        val btnEdit : Button = view.findViewById(R.id.editBtn)

        btnComplete.setOnClickListener(){
            Toast.makeText(context, "Congratulations!", Toast.LENGTH_SHORT).show()
        }

        btnEdit.setOnClickListener(){ v:View ->

            val activity=v!!.context as AppCompatActivity
            val fragmentEditGoals = GoalsEditFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentEditGoals).addToBackStack(null).commit()
        }



        return view

    }

}
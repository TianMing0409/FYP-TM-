package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.moodmonitoringapp.R

class GoalsEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_goals_edit,container,false)

        val btnSave: Button =view.findViewById(R.id.saveBtn)
        val btnDelete : Button = view.findViewById(R.id.delBtn)

        btnSave.setOnClickListener(){
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener(){
            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}
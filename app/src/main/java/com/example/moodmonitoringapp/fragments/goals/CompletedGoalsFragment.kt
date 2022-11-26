package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.RecyclerAdapter
import com.example.moodmonitoringapp.databinding.FragmentCompletedGoalsBinding

class CompletedGoalsFragment : Fragment(R.layout.fragment_completed_goals) {

    //private lateinit var binding : FragmentCompletedGoalsBinding
    //private val binding by viewBinding(FragmentCompletedGoalsBinding::bind)
    private lateinit var binding : FragmentCompletedGoalsBinding

    private var titleList = mutableListOf<String>()
    private var dateList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    companion object {
        fun newInstance() = CompletedGoalsFragment()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentCompletedGoalsBinding.inflate(inflater,container,false)
//
//        return binding.root
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompletedGoalsBinding.inflate(inflater,container,false)

        postToList()

        binding.goalRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.goalRecyclerView.adapter = RecyclerAdapter(titleList,dateList,imageList)




        return binding.root
    }

    //Dummy function to test data
    private fun addToList(title:String, date: String,image: Int){
        titleList.add(title)
        dateList.add(date)
        imageList.add(image)
    }

    //Dummy function to test data
    private fun postToList(){
        for(i in 1..25){
            addToList("Completed Goal Title $i","Date $i",R.drawable.keyboard_arrow_right_icon)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }



}
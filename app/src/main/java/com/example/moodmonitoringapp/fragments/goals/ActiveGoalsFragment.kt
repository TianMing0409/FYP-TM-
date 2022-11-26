package com.example.moodmonitoringapp.fragments.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.RecyclerAdapter
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding


class ActiveGoalsFragment : Fragment(R.layout.fragment_active_goals) {

    private lateinit var binding : FragmentActiveGoalsBinding
    //private val binding by viewBinding(FragmentActiveGoalsBinding::bind)

    private var titleList = mutableListOf<String>()
    private var dateList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()


    companion object {
        fun newInstance() = ActiveGoalsFragment()
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
            addToList("Active Goal Title $i","Date $i",R.drawable.keyboard_arrow_right_icon)
        }
    }

}
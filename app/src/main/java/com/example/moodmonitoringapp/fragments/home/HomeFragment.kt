package com.example.moodmonitoringapp.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.databinding.FragmentHomeBinding
import com.example.moodmonitoringapp.databinding.FragmentRecommendationBinding
import com.example.moodmonitoringapp.fragments.recommendation.RecommendationFragment

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view =inflater.inflate(R.layout.fragment_home,container,false)
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.btnRecomm.setOnClickListener(){
            val recommDialog = RecommendationFragment()

            recommDialog.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }


        return binding.root
    }

}
package com.example.moodmonitoringapp.fragments.communityPlatform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.databinding.FragmentCreatePostBinding

class CreatePostFragment : Fragment() {

    private lateinit var binding : FragmentCreatePostBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(inflater,container,false)


        binding.shareBtn.setOnClickListener(){v:View ->
            Toast.makeText(context, "Share Successfully", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

}
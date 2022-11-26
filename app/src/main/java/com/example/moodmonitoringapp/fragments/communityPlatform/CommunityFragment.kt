package com.example.moodmonitoringapp.fragments.communityPlatform

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.GoalRecyclerAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment

class CommunityFragment : Fragment(R.layout.fragment_community) {

    private lateinit var binding : FragmentCommunityBinding

    //Testing purpose variables (Need to modify)
    private var usernameList = mutableListOf<String>()
    private var postDateList = mutableListOf<String>()
    private var postDetailsList = mutableListOf<String>()
    private var likeCountList = mutableListOf<String>()
    private var commentCountList = mutableListOf<String>()
    private var profileImageList = mutableListOf<Int>()


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

        binding = FragmentCommunityBinding.inflate(inflater,container,false)

        postToList()

        binding.postRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.postRecyclerView.adapter = PostRecyclerAdapter(usernameList, postDateList, postDetailsList, likeCountList,
            commentCountList, profileImageList)

        binding.createPostBtn.setOnClickListener(){
            Toast.makeText(context, "Create Post", Toast.LENGTH_SHORT).show()
        }

        binding.postRecyclerView

        return binding.root

    }

    //Dummy function to test data
    private fun addToList(username:String, postDate: String, postDetails:String,likeCount:String,commentCount:String,profileImage: Int){
        usernameList.add(username)
        postDateList.add(postDate)
        postDetailsList.add(postDetails)
        likeCountList.add(likeCount)
        commentCountList.add(commentCount)
        profileImageList.add(profileImage)
    }

    //Dummy function to test data
    private fun postToList(){
        for(i in 1..15){
            addToList("Username $i","Date $i","Post Details $i","Like Count $i",
                "Comment Count $i", R.drawable.profile_icon)
        }
    }



}
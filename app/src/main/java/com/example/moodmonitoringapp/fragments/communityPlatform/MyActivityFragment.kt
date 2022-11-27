package com.example.moodmonitoringapp.fragments.communityPlatform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.databinding.FragmentCreatePostBinding
import com.example.moodmonitoringapp.databinding.FragmentMyActivityBinding

class MyActivityFragment : Fragment() {

    private lateinit var binding : FragmentMyActivityBinding

    //Testing purpose variables (Need to modify)
    private var usernameList = mutableListOf<String>()
    private var postDateList = mutableListOf<String>()
    private var postDetailsList = mutableListOf<String>()
    private var likeCountList = mutableListOf<String>()
    private var commentCountList = mutableListOf<String>()
    private var profileImageList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyActivityBinding.inflate(inflater,container,false)

        postToList()


        binding.myPostRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.myPostRecyclerView.adapter = PostRecyclerAdapter(usernameList, postDateList, postDetailsList, likeCountList,
            commentCountList, profileImageList)


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
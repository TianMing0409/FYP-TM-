package com.example.moodmonitoringapp.fragments.communityPlatform

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.GoalRecyclerAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class CommunityFragment : Fragment(R.layout.fragment_community) {

    private lateinit var binding : FragmentCommunityBinding

    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Posts>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

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

        //postToList()

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Posts")

        userRecyclerView = binding.postRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Posts>()

        getPostsData()


//        binding.postRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.postRecyclerView.adapter = PostRecyclerAdapter(usernameList, postDateList, postDetailsList, likeCountList,
//            commentCountList, profileImageList)
//
        binding.createPostBtn.setOnClickListener(){v:View ->
            val activity=v!!.context as AppCompatActivity
            val fragmentCreatePost = CreatePostFragment()

            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentCreatePost ).addToBackStack(null).commit()
        }
//
//        binding.postRecyclerView
//
        return binding.root

    }
    private fun getPostsData(){

        val getData = db

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(postSnapshot in snapshot.children){
                        val posts = postSnapshot.getValue(Posts::class.java)
                        userArrayList.add(posts!!)
                    }
                    userRecyclerView.adapter = PostRecyclerAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

//    private fun deletePost(postID: String) {
//
//        db.child(postID).removeValue()
//
//        Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show()
//
//        replaceFragment(CommunityFragment())     // Need to change replace dashboard fragment
//    }

//    //Dummy function to test data
//    private fun addToList(username:String, postDate: String, postDetails:String,likeCount:String,commentCount:String,profileImage: Int){
//        usernameList.add(username)
//        postDateList.add(postDate)
//        postDetailsList.add(postDetails)
//        likeCountList.add(likeCount)
//        commentCountList.add(commentCount)
//        profileImageList.add(profileImage)
//    }
//
//    //Dummy function to test data
//    private fun postToList(){
//        for(i in 1..15){
//            addToList("Username $i","Date $i","Post Details $i","Like Count $i",
//                "Comment Count $i", R.drawable.profile_icon)
//        }
//    }



}
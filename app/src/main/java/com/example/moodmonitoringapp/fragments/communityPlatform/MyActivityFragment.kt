package com.example.moodmonitoringapp.fragments.communityPlatform

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.MyActivityRecyclerAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCreatePostBinding
import com.example.moodmonitoringapp.databinding.FragmentMyActivityBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard.PassCommData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class MyActivityFragment : Fragment(), PassCommData {

    private lateinit var binding : FragmentMyActivityBinding

    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Posts>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    //Testing purpose variables (Need to modify)
//    private var usernameList = mutableListOf<String>()
//    private var postDateList = mutableListOf<String>()
//    private var postDetailsList = mutableListOf<String>()
//    private var likeCountList = mutableListOf<String>()
//    private var commentCountList = mutableListOf<String>()
//    private var profileImageList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyActivityBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Posts")

        userRecyclerView = binding.myPostRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Posts>()

        myPostsData()

//        postToList()


//        binding.myPostRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.myPostRecyclerView.adapter = PostRecyclerAdapter(usernameList, postDateList, postDetailsList, likeCountList,
//            commentCountList, profileImageList)


        return binding.root
    }

    private fun myPostsData(){

        val getData = db

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    userArrayList.clear()
                    for (postSnapshot in snapshot.children) {
                        val posts = postSnapshot.getValue(Posts::class.java)
                        if(posts!!.postUserID == userUId){
                            userArrayList.add(posts!!)
                        }


                    }
                        userRecyclerView.adapter =
                            MyActivityRecyclerAdapter(userArrayList, this@MyActivityFragment)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    override fun passCommData(position: Int, postID: String, postUsername: String, postDate: String, postDetails: String
                              , commentCount: Int, postImage : String,postUserID : String)
    {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_post_id",postID)
        bundle.putString("input_post_username", postUsername)
        bundle.putString("input_post_date",postDate)
        bundle.putString("input_post_details", postDetails)
        bundle.putInt("input_comment_count",commentCount)
        bundle.putString("input_post_image",postImage)
        bundle.putString("input_post_userID",postUserID)

        val transaction = this.parentFragmentManager.beginTransaction()
        val commentFragment =CommentFragment()
        commentFragment.arguments = bundle

        transaction.replace(R.id.fragment_container, commentFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

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
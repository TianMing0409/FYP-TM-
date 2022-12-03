package com.example.moodmonitoringapp.fragments.communityPlatform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentMyActivityBinding
import com.example.moodmonitoringapp.databinding.FragmentMyBookmarkBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList


class MyBookmarkFragment : Fragment() {

    private lateinit var binding : FragmentMyBookmarkBinding

    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Posts>
    private lateinit var auth : FirebaseAuth
    private var userUId = "dwsZDErsUGRoNyD9UAvkyYTCSyd2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_my_bookmark, container, false)
        binding = FragmentMyBookmarkBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Posts")

        userRecyclerView = binding.bookmarkRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Posts>()

        //getPostsData()


        return binding.root
    }

//    private fun getPostsData(){
//
//        val getData = db
//
//        getData.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if(snapshot.exists()){
//                    userArrayList.clear()
//                    for(postSnapshot in snapshot.children){
//                        val posts = postSnapshot.getValue(Posts::class.java)
//                        userArrayList.add(posts!!)
//                    }
//                    userRecyclerView.adapter = PostRecyclerAdapter(userArrayList,this@MyBookmarkFragment)
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }

}
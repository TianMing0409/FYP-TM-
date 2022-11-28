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
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.databinding.FragmentCreatePostBinding
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class CreatePostFragment : Fragment() {

    private lateinit var binding : FragmentCreatePostBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""
    private var username = "Hardcoded"                      //Hardcoded username, need to clear it when real work

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Posts")

        binding.shareBtn.setOnClickListener(){v:View ->
            validateData()
            Toast.makeText(context, "Share Successfully", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

    private var postID=""
    private var postUsername=""
    private var postDate = ""
    private var postDetails = ""
    private var likeCount= 0
    private var commentCount = 0
    private var dt = ""

    private fun validateData(){

        postDetails = binding.inputPost.text.toString().trim()

        if(postDetails.isEmpty()){
            Toast.makeText(context, "Enter post details...", Toast.LENGTH_SHORT).show()
        }
        else{

            uploadPost(postDetails,username)
            Toast.makeText(context, "Share Successfully!", Toast.LENGTH_SHORT).show()

            replaceFragment(CommunityFragment())   // Need to change replace community dashboard fragment
        }
    }
    private fun uploadPost(postDetails : String,username : String) {

        val c: Calendar = Calendar.getInstance()
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dt = sdf.format(c.time);

        val postID = "P" + (0..9000).random()
        val postDate = dt
        val likeCount = 0
        val commentCount = 0

        val post = Posts(postID,username ,postDate, postDetails,likeCount,commentCount)

        db.child(postID).setValue(post).addOnSuccessListener {
                binding.inputPost.text.clear()
            }.addOnFailureListener{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null ){

            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.commit()
        }
    }


}
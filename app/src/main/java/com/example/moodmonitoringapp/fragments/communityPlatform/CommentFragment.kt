package com.example.moodmonitoringapp.fragments.communityPlatform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.CommentAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Comments
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCommentBinding
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class CommentFragment : Fragment() {

    private lateinit var binding : FragmentCommentBinding

    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Comments>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    var inputPos: Int? = null
    var inputPostId: String = ""
    var inputPostUsername: String = ""
    var inputPostDate: String = ""
    var inputPostContent: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_comment, container, false)
        binding = FragmentCommentBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Comments")

        //fetch data
        inputPos = arguments?.getInt("input_pos")
        inputPostId = arguments?.getString("input_post_id").toString()
        inputPostUsername = arguments?.getString("input_post_username").toString()
        inputPostDate = arguments?.getString("input_post_date").toString()
        inputPostContent = arguments?.getString("input_post_details").toString()

        binding.postUsername?.text = inputPostUsername
        binding.postDate?.text = inputPostDate
        binding.postContent?.text = inputPostContent


        userRecyclerView = binding.commentRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Comments>()

        getCommentsData()

        binding.commentBtn.setOnClickListener(){
            Toast.makeText(context, "Commented!", Toast.LENGTH_SHORT).show()
        }


        return binding.root

    }

    private fun getCommentsData(){

        val getData = db

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(commentSnapshot in snapshot.children){
                        val comments = commentSnapshot.getValue(Comments::class.java)
                        userArrayList.add(comments!!)
                    }
                    userRecyclerView.adapter = CommentAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}
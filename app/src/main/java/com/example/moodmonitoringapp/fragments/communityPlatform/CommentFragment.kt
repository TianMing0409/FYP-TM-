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
import com.google.firebase.database.ktx.getValue
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class CommentFragment : Fragment() {

    private lateinit var binding : FragmentCommentBinding


    private lateinit var db : DatabaseReference
    private lateinit var db2 : DatabaseReference
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
    var inputPostImage : String = ""
    var inputPostCommentCount : Int = 0

    private var commentDetails = ""
    private var dt = ""


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
        db = FirebaseDatabase.getInstance().getReference("Posts")

        //fetch data
        inputPos = arguments?.getInt("input_pos")
        inputPostId = arguments?.getString("input_post_id").toString()
        inputPostUsername = arguments?.getString("input_post_username").toString()
        inputPostDate = arguments?.getString("input_post_date").toString()
        inputPostContent = arguments?.getString("input_post_details").toString()
        inputPostImage = arguments?.getString("input_post_image").toString()
        inputPostCommentCount = arguments?.getInt("input_comment_count")!!.toInt()

//        binding.postUsername?.text = inputPostUsername
//        binding.postDate?.text = inputPostDate
//        binding.postContent?.text = inputPostContent
//        if(inputPostImage == ""){
//            binding.postImg.setImageBitmap(null)
//        }else{
//            Picasso.get().load(inputPostImage).into(binding.postImg)
//        }



        userRecyclerView = binding.commentRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Comments>()

        getCommentsData(inputPostId)

        binding.post.setOnClickListener(){

            commentDetails = binding.inputComment.text.toString().trim()
            addComment(inputPostId,commentDetails)
            updateCommentCount(inputPostId,inputPostCommentCount)
            Toast.makeText(context, "Comment Successfully!", Toast.LENGTH_SHORT).show()

        }

        return binding.root

    }

    private fun getCommentsData(postId : String){

        val getData = db.child(postId).child("Comments")

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    userArrayList.clear()
                    for(commentSnapshot in snapshot.children){
                        val comments = commentSnapshot.getValue(Comments::class.java)
                        userArrayList.add(comments!!)
//                        val postId = commentSnapshot.child("postId").value
//                        userArrayList.add(postId)
                    }
                    userRecyclerView.adapter = CommentAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun  updateCommentCount(postId : String, commentCount : Int){
        db.child(postId).child("commentCount").setValue(commentCount+1).addOnSuccessListener {
            binding.inputComment.text.clear()
        }.addOnFailureListener{
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }

        //Update bookmark comment count
        db2 = FirebaseDatabase.getInstance().getReference("Bookmarks")
        db2.child(userUId).child(postId).child("commentCount").setValue(commentCount+1).addOnSuccessListener {
            binding.inputComment.text.clear()
        }.addOnFailureListener{
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addComment(postId : String, commentDetails: String){
        val commentID = "C" + (0..10000).random()

        val c: Calendar = Calendar.getInstance()
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dt = sdf.format(c.time);
        val commentDate = dt
        val commentUsername = "Hardcoded commenter 1"                 //Need to get the username from user in real case


        val comments = Comments(commentID, commentUsername, commentDate, commentDetails, postId)
        db.child(postId).child("Comments").child(commentID).setValue(comments).addOnSuccessListener {
            binding.inputComment.text.clear()
        }.addOnFailureListener{
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

}
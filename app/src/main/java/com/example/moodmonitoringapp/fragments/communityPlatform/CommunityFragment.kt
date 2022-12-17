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
import com.example.moodmonitoringapp.adapter.BookmarkAdapter
import com.example.moodmonitoringapp.adapter.GoalRecyclerAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Bookmarks
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard.CommunityDashboardPagerAdapter
import com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard.PassCommData
import com.example.moodmonitoringapp.fragments.goals.ActiveGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CommunityFragment : Fragment(R.layout.fragment_community), PassCommData {

    private lateinit var binding : FragmentCommunityBinding

    private lateinit var db : DatabaseReference
    private lateinit var db2 : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Posts>
    private lateinit var bookmarkArrayList : ArrayList<Bookmarks>
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
        db2 = FirebaseDatabase.getInstance().getReference("Bookmarks")

        userRecyclerView = binding.postRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Posts>()
        bookmarkArrayList = arrayListOf<Bookmarks>()

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
                    userArrayList.clear()
                    for(postSnapshot in snapshot.children){
                        val posts = postSnapshot.getValue(Posts::class.java)
                        userArrayList.add(posts!!)
                    }
                    userRecyclerView.adapter = PostRecyclerAdapter(userArrayList,this@CommunityFragment)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun passCommData(position: Int, postID: String, postUsername: String, postDate: String, postDetails: String
                              , commentCount: Int, imageUrl : String, postUserID : String)
    {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_post_id",postID)
        bundle.putString("input_post_username", postUsername)
        bundle.putString("input_post_date",postDate)
        bundle.putString("input_post_details", postDetails)
        bundle.putInt("input_comment_count",commentCount)
        bundle.putString("input_post_image",imageUrl)
        bundle.putString("input_post_userID",postUserID)

        val transaction = this.parentFragmentManager.beginTransaction()
        val commentFragment =CommentFragment()
        commentFragment.arguments = bundle

        transaction.replace(R.id.fragment_container, commentFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }



}
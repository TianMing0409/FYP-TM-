package com.example.moodmonitoringapp.fragments.communityPlatform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentCommentBinding
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class CommentFragment : Fragment() {

    private lateinit var binding : FragmentCommentBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_comment, container, false)
        binding = FragmentCommentBinding.inflate(inflater,container,false)

        binding.commentBtn.setOnClickListener(){
            Toast.makeText(context, "Commented!", Toast.LENGTH_SHORT).show()
        }


        return binding.root

    }

}
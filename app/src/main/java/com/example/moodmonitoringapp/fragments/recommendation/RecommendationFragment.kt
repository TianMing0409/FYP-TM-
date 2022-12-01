package com.example.moodmonitoringapp.fragments.recommendation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentCommunityBinding
import com.example.moodmonitoringapp.databinding.FragmentRecommendationBinding
import com.example.moodmonitoringapp.fragments.communityPlatform.CreatePostFragment
import com.example.moodmonitoringapp.fragments.goals.AddGoalsFragment
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.DashBoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class RecommendationFragment : DialogFragment() {

    private lateinit var binding : FragmentRecommendationBinding

    private lateinit var db : DatabaseReference
    private lateinit var userArrayList : ArrayList<Posts>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    private var goalID=""
    private var goalName=""
    private var goalTargetDate = ""
    private var goalStatus = "Active"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecommendationBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Posts")


        userArrayList = arrayListOf<Posts>()

        val recommendation = binding.recommendation           //Get the recommendation provided

        binding.okBtn.setOnClickListener(){
            dismiss()
        }

        binding.addRecommBtn.setOnClickListener(){
            val recomm = recommendation.text.toString()

            val bundle = Bundle()
            bundle.putString("recomm_goal",recomm)

            val transaction = this.parentFragmentManager.beginTransaction()
            val addGoalsFragment = AddGoalsFragment()
            addGoalsFragment.arguments = bundle

            dismiss()      // Close the dialog fragment
            transaction.replace(R.id.fragment_container, addGoalsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            //Toast.makeText(context, "Go to add goals page", Toast.LENGTH_SHORT).show()

        }


        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null ){

            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(1000,1100)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


}
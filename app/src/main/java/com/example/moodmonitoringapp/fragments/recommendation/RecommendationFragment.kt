package com.example.moodmonitoringapp.fragments.recommendation

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentRecommendationBinding
import com.example.moodmonitoringapp.fragments.goals.AddGoalsFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class RecommendationFragment : DialogFragment() {

    private lateinit var binding : FragmentRecommendationBinding

    private lateinit var db : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecommendationBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Moods")


        binding.cancelBtn.setOnClickListener(){
            dismiss()
        }


        val linkGoaltv = binding.goToGoalLink
        val content = SpannableString("Click here to set your own goal")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        linkGoaltv.text = content

        binding.goToGoalLink.setOnClickListener(){

            dismiss()
            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
            val addGoalFragment = AddGoalsFragment()

            fragmentTransaction.replace(R.id.fragment_container,addGoalFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        getMoodData()   // Get user mood condition

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

//    override fun onStart() {
//        super.onStart()
//
//        dialog?.window?.setLayout(1000,1100)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    }

    private fun getRecommMusic(userMood : String): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")

        return pythonFile.callAttr("recomm_music",userMood).toString()
    }

    private fun getRecommMusicDay(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMusic_day").toString()
    }

    private fun getRecommMusicMonth(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMusic_month").toString()
    }

    private fun getRecommMusicYear(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMusic_year").toString()
    }

    private fun getRecommMovie(userMood : String): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recomm_movie",userMood).toString()
    }

    private fun getRecommMovieDay(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMovie_day").toString()
    }

    private fun getRecommMovieMonth(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMovie_month").toString()
    }

    private fun getRecommMovieYear(): String{

        val python : Python = Python.getInstance()

        val pythonFile : PyObject = python.getModule("MoodRecommendationModule")
        return pythonFile.callAttr("recommMovie_year").toString()
    }


    private fun getMoodData() {

        val getData = db

        getData.child(userUId).get().addOnSuccessListener {
            val mood = it.child("mood").value.toString()
            binding.inputMood.setText(mood)
            binding.recommendationMusic.text = getRecommMusic(mood)
            binding.recommendationMovie.text = getRecommMovie(mood)

//            binding.inputRecommDate.text = getRecommendationDate()

            //Add music recommendation as goal (Direct to Add goal page)
            binding.addRecommMusicBtn.setOnClickListener(){
                val recommMusic = binding.recommendationMusic.text.toString()
                val recommMusic_day = getRecommMusicDay().toInt()
                val recommMusic_month = getRecommMusicMonth().toInt()  //Minus 1 month to get proper month
                val recommMusic_year = getRecommMusicYear().toInt()

                val bundle = Bundle()
                bundle.putString("recomm_goal",recommMusic)
                bundle.putInt("recomm_day",recommMusic_day)
                bundle.putInt("recomm_month",recommMusic_month)
                bundle.putInt("recomm_year",recommMusic_year)

                val transaction = this.parentFragmentManager.beginTransaction()
                val addGoalsFragment = AddGoalsFragment()
                addGoalsFragment.arguments = bundle

                dismiss()      // Close the dialog fragment
                transaction.replace(R.id.fragment_container, addGoalsFragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }

            //Add movie recommendation as goal (Direct to Add goal page)
            binding.addRecommMovieBtn.setOnClickListener(){
                val recommMovie = binding.recommendationMovie.text.toString()
                val recommMovie_day = getRecommMovieDay().toInt()
                val recommMovie_month = getRecommMovieMonth().toInt()    //Minus 1 month to get proper month
                val recommMovie_year = getRecommMovieYear().toInt()

                val bundle = Bundle()
                bundle.putString("recomm_goal",recommMovie)
                bundle.putInt("recomm_day",recommMovie_day)
                bundle.putInt("recomm_month",recommMovie_month)
                bundle.putInt("recomm_year",recommMovie_year)

                val transaction = this.parentFragmentManager.beginTransaction()
                val addGoalsFragment = AddGoalsFragment()
                addGoalsFragment.arguments = bundle

                dismiss()      // Close the dialog fragment
                transaction.replace(R.id.fragment_container, addGoalsFragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }



        }
    }


}
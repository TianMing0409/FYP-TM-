package com.example.moodmonitoringapp.fragments.goals

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.databinding.FragmentCongratulationBinding
import com.example.moodmonitoringapp.databinding.FragmentRecommendationBinding

class CongratulationFragment : DialogFragment() {

    private lateinit var binding : FragmentCongratulationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_congratulation, container, false)

        val percentage = 65
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)

        binding = FragmentCongratulationBinding.inflate(inflater,container,false)

        binding.congratulationAnimation.playAnimation()
        binding.congratGreet.playAnimation()

        binding.okCongratBtn.setOnClickListener(){
            dismiss()
        }


        return binding.root

    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(1000,1100)
    }

}
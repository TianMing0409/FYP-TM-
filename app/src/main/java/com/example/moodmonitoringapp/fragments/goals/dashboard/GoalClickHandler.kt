package com.example.moodmonitoringapp.fragments.goals.dashboard

import com.example.moodmonitoringapp.data.Goals

interface Communicator {
    fun passData(position: Int,
                 goalID : String,
                 goal_title: String,
                 goal_status: String ,
                 goal_target_date: String)
}
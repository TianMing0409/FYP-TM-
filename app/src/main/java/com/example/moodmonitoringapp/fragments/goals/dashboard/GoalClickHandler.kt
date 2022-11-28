package com.example.moodmonitoringapp.fragments.goals.dashboard

import com.example.moodmonitoringapp.data.Goals

interface Communicator {
    fun passData(position: Int, goal_title: String, goal_target_date: String)
}
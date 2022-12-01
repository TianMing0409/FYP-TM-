package com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard

interface PassCommData {
    fun passCommData(position: Int,
                     postID : String,
                     postUsername: String,
                     postDate: String ,
                     postDetails: String,
                     likeCount: Int,
                     commentCount:Int)

}
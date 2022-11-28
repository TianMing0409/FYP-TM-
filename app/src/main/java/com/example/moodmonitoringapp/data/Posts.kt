package com.example.moodmonitoringapp.data

data class Posts(
    var postID:String ="",
    var postUsername:String="",
    var postDate:String ="",
    var postDetails:String="",
    var likeCount: Int=0,
    var commentCount: Int=0
)

package com.example.moodmonitoringapp.data

data class Posts(
    var postID:String ="",
    var postUsername:String="",
    var postDate:String ="",
    var postDetails:String="",
    var commentCount: Int=0,
    var imageUrl : String = "",
    var postUserID : String = ""
//    var comments : ArrayList<Comments>
)

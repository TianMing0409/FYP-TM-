package com.example.moodmonitoringapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.fragments.communityPlatform.CommentFragment
import com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard.PassCommData
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class PostRecyclerAdapter (private val posts: ArrayList<Posts>, private val listener: PassCommData) :
    RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>(){

    private lateinit var db : DatabaseReference

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val username: TextView = itemView.findViewById(R.id.userName)
        val postDate : TextView = itemView.findViewById(R.id.postDate)
        val postDetails : TextView = itemView.findViewById(R.id.postDetails)
        val bookmarkIcon : ImageView = itemView.findViewById(R.id.bookmarkIcon)
        val commentCount : TextView = itemView.findViewById(R.id.commentCount)
        val commentIcon : ImageView = itemView.findViewById(R.id.commentIcon)
        var postImage:ImageView = itemView.findViewById(R.id.postImage)
//        private val dotSetting : ImageView = itemView.findViewById(R.id.dotSetting)


        init {
            itemView.setOnClickListener(this)
            //v: View ->
//                val position: Int = adapterPosition
//                Toast.makeText(itemView.context, "You clicked on item# ${position+1}", Toast.LENGTH_SHORT).show()
//                val activity=v!!.context as AppCompatActivity
//                val fragmentDetails = GoalsDetailsFragment()
//                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentDetails).addToBackStack(null).commit()

//            dotSetting.setOnClickListener(){
//                popupMenus(it)
//            }

            bookmarkIcon.setOnClickListener(){
                //Fact function
                val itemId = posts[adapterPosition].postID
                val itemtUsername = posts[adapterPosition].postUsername
                val itemDate = posts[adapterPosition].postDate
                val itemDetails = posts[adapterPosition].postDetails
                val itemCommentCount = posts[adapterPosition].commentCount
                val itemImage = posts[absoluteAdapterPosition].imageUrl
                val itemUserID = posts[absoluteAdapterPosition].postUserID
                bookmark(itemId,itemtUsername,itemDate, itemDetails,itemCommentCount,itemImage,itemUserID)
                Toast.makeText(itemView.context, "Bookmarked", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onClick(v: View?) {
            commentIcon.setOnClickListener() {

                val position = adapterPosition
                val itemId = posts[adapterPosition].postID
                val itemtUsername = posts[adapterPosition].postUsername
                val itemDate = posts[adapterPosition].postDate
                val itemDetails = posts[adapterPosition].postDetails
                val itemCommentCount = posts[adapterPosition].commentCount
                val itemImage = posts[absoluteAdapterPosition].imageUrl
                val itemUserID = posts[absoluteAdapterPosition].postUserID
                if (position != RecyclerView.NO_POSITION) {
                    listener.passCommData(
                        position,
                        itemId,
                        itemtUsername,
                        itemDate,
                        itemDetails,
                        itemCommentCount,
                        itemImage,
                        itemUserID
                    )
                }
            }
        }

//        private fun popupMenus(v:View){
//
//            val popupMenus = PopupMenu(itemView.context,v)
//            popupMenus.inflate(R.menu.post_menu)
//            popupMenus.setOnMenuItemClickListener {
//                when(it.itemId){
//                    R.id.deletePost ->{
//
//                        Toast.makeText(itemView.context,"Delete Post",Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    else -> true
//                }
//            }
//            popupMenus.show()
//            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
//            popup.isAccessible = true
//            val menu = popup.get(popupMenus)
//            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v  = LayoutInflater.from(parent.context).inflate(R.layout.public_post_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = posts[position]
        holder.username.text = currentItem.postUsername
        holder.postDate.text = currentItem.postDate
        holder.postDetails.text = currentItem.postDetails
//        holder.commentCount.text = currentItem.commentCount.toString()
        holder.commentCount.setText(currentItem.commentCount.toString()+" comments")
//        Picasso.get().load(currentItem.imageUrl).into(holder.postImage)
        if(currentItem.imageUrl.toString() == ""){
            holder.postImage.setImageBitmap(null)
        }else{
            Picasso.get().load(currentItem.imageUrl).into(holder.postImage)
        }


    }

    override fun getItemCount(): Int {
        return posts.size
    }

    private fun bookmark(postId : String,postUsername : String,postDate: String, postDetails : String,
                         commentCount : Int, imageUrl : String, postUserID : String){

        db = FirebaseDatabase.getInstance().getReference("Bookmarks")

        val bookmarkID = "B" + (0..9000).random()

        val bookmark = Posts(postId, postUsername,postDate, postDetails,commentCount,imageUrl,postUserID)


//        db.child(userUId)
//            .child(goalID).setValue(goal).addOnSuccessListener {
//                binding.inputGoal.text.clear()
//                //binding.inputDate.text.clear()
//
//            }.addOnFailureListener{
//
//            }
    }

}
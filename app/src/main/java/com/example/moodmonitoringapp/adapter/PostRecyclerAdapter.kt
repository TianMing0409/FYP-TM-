package com.example.moodmonitoringapp.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.fragments.communityPlatform.CommentFragment
import com.example.moodmonitoringapp.fragments.communityPlatform.CommunityFragment
import com.example.moodmonitoringapp.fragments.communityPlatform.CreatePostFragment
import com.example.moodmonitoringapp.fragments.communityPlatform.communityDashboard.PassCommData
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment
import com.example.moodmonitoringapp.fragments.goals.dashboard.Communicator
import com.google.firebase.database.*
import org.w3c.dom.Text

class PostRecyclerAdapter (private val posts: ArrayList<Posts>, private val listener: PassCommData) :
    RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        var like: Int = 0    //Initial value, not sure need or not after connect to database

        val username: TextView = itemView.findViewById(R.id.userName)
        val postDate : TextView = itemView.findViewById(R.id.postDate)
        val postDetails : TextView = itemView.findViewById(R.id.postDetails)
        val likeCount: TextView = itemView.findViewById(R.id.loveCount)
        val likeIcon : ImageView = itemView.findViewById(R.id.loveIcon)
        val commentCount : TextView = itemView.findViewById(R.id.commentCount)
        val commentIcon : ImageView = itemView.findViewById(R.id.commentIcon)
        private val dotSetting : ImageView = itemView.findViewById(R.id.dotSetting)

        init {
            itemView.setOnClickListener{ //v: View ->
//                val position: Int = adapterPosition
//                Toast.makeText(itemView.context, "You clicked on item# ${position+1}", Toast.LENGTH_SHORT).show()
//                val activity=v!!.context as AppCompatActivity
//                val fragmentDetails = GoalsDetailsFragment()
//                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentDetails).addToBackStack(null).commit()
            }
            dotSetting.setOnClickListener(){
                popupMenus(it)
            }

            likeIcon.setOnClickListener(){
                //Fact function
                like++
                likeCount.text = like.toString()

                Toast.makeText(itemView.context, "Liked", Toast.LENGTH_SHORT).show()
            }

            commentIcon.setOnClickListener(){v:View ->
                val activity=v!!.context as AppCompatActivity
                val fragmentComment = CommentFragment()

                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentComment ).addToBackStack(null).commit()
                //Fact function
                //Toast.makeText(itemView.context, "Commented", Toast.LENGTH_SHORT).show()
            }

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val itemId = posts[adapterPosition].postID
            val itemtUsername = posts[adapterPosition].postUsername
            val itemDate = posts[adapterPosition].postDate
            val itemDetails = posts[adapterPosition].postDetails
            val itemLikeCount = posts[adapterPosition].likeCount
            val itemCommentCount = posts[adapterPosition].commentCount
            if(position!= RecyclerView.NO_POSITION){
                listener.passCommData(position,itemId,itemtUsername,itemDate,itemDetails,itemLikeCount,itemCommentCount)
            }
        }

        private fun popupMenus(v:View){

            val popupMenus = PopupMenu(itemView.context,v)
            popupMenus.inflate(R.menu.post_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.reportPost ->{
                        Toast.makeText(itemView.context,"Report Post",Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.deletePost ->{
//                        db.addValueEventListener(object : ValueEventListener {
//                            override fun onDataChange(snapshot: DataSnapshot) {
//
//                                if(snapshot.exists()){
//                                    for(postSnapshot in snapshot.children){
//                                        val postId = postSnapshot.child("postID").value
//                                        deletePost(postId.toString())
//                                    }
//
//                                }
//                            }
//
//                            override fun onCancelled(error: DatabaseError) {
//
//                            }
//
//                        })
                        //deletePost(postID)
                        Toast.makeText(itemView.context,"Delete Post",Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v  = LayoutInflater.from(parent.context).inflate(R.layout.post_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = posts[position]
        holder.username.text = currentItem.postUsername
        holder.postDate.text = currentItem.postDate
        holder.postDetails.text = currentItem.postDetails
        holder.likeCount.text = currentItem.likeCount.toString()
        holder.commentCount.text = currentItem.commentCount.toString()

    }

    override fun getItemCount(): Int {
        return posts.size
    }

//    private fun deletePost(postID: String) {
//
////        db.child(postID).removeValue()
//
//        //replaceFragment(CommunityFragment())     // Need to change replace dashboard fragment
//    }

//    private fun replaceFragment(fragment: Fragment){
//        if(fragment!=null ){
//
//            val fragmentTransaction  = this.parentFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container,fragment)
//            fragmentTransaction.commit()
//        }
//    }

}
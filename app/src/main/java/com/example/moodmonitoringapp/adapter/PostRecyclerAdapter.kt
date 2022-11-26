package com.example.moodmonitoringapp.adapter

import android.content.Context
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
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment
import org.w3c.dom.Text

class PostRecyclerAdapter (private var username: List<String>, private var postDate: List<String>, private var postDetails:List<String>,
                           private var likeCount:List<String>, private var commentCount:List<String>, private var profileImage:List<Int>) :
    RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val userName: TextView = itemView.findViewById(R.id.userName)
        val postDate : TextView = itemView.findViewById(R.id.postDate)
        val postDetails : TextView = itemView.findViewById(R.id.postDetails)
        val likeCount: TextView = itemView.findViewById(R.id.loveCount)
        val commentCount : TextView = itemView.findViewById(R.id.commentCount)
        val profileImage : ImageView = itemView.findViewById(R.id.profilePicture)
        val dotSetting : ImageView = itemView.findViewById(R.id.dotSetting)

        init {
            itemView.setOnClickListener{ v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item# ${position+1}", Toast.LENGTH_SHORT).show()
//                val activity=v!!.context as AppCompatActivity
//                val fragmentDetails = GoalsDetailsFragment()
//                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentDetails).addToBackStack(null).commit()
            }
            dotSetting.setOnClickListener(){
                popupMenus(it)
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
        holder.userName.text = username[position]
        holder.postDate.text = postDate[position]
        holder.postDetails.text = postDetails[position]
        holder.likeCount.text = likeCount[position]
        holder.commentCount.text = commentCount[position]
        holder.profileImage.setImageResource(profileImage[position])

    }

    override fun getItemCount(): Int {
        return username.size
    }

}
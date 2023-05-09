package com.example.finalproject2022r

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chef_resturent.*

class myadapter(var context: Context, var datalist: ArrayList<model>) : RecyclerView.Adapter<myadapter.myviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items1, parent, false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val broad = datalist.get(position)
        holder.t1.text = broad.name
        holder.t1.setOnClickListener {
      var intent = Intent(context, UpdateDeleteRes::class.java)
       intent.putExtra("rest_name", holder.t1.text)
       context.startActivity(intent)

   }
    }



    override fun getItemCount(): Int {
        return datalist!!.size
    }

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var t1: TextView
        init {
            t1 = itemView.findViewById(R.id.t1)
        }
    }


}
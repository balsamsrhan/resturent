package com.example.finalproject2022r

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdapterResturents (var context: Context, var datalist: ArrayList<model>) : RecyclerView.Adapter<AdapterResturents.myviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items32, parent, false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val broad = datalist.get(position)
        holder.t1.text = broad.name
        holder.t1.setOnClickListener {
        var intent = Intent(context,UpdateDeleteMeals::class.java)
            context.startActivity(intent)
        }
     }


    override fun getItemCount(): Int {
        return datalist!!.size
    }

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var t1: TextView
         //var edite:FloatingActionButton
        init {
            t1 = itemView.findViewById(R.id.t222)
           // edite=itemView.findViewById(R.id._editemel)
        }
    }




}
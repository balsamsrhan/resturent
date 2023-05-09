package com.example.finalproject2022r

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapterstatic (var context: Context, var arr:ArrayList<model>): RecyclerView.Adapter<Adapterstatic.myholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items1, parent, false)
        return myholder(view)
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        val broad = arr.get(position)
        holder.t1.text = broad.name
    }

    override fun getItemCount(): Int {
        return arr!!.size
    }
    inner class myholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var t1: TextView
        init {
            t1 = itemView.findViewById(R.id.t1)
        }
    }
}

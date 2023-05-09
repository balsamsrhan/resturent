package com.example.finalproject2022r

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMeal2 (var context:Context, var arr:ArrayList<model>):RecyclerView.Adapter<AdapterMeal2.myholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMeal2.myholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items3, parent, false)
        return myholder(view)
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        val broad = arr.get(position)
        holder.t1.text = broad.name
        holder.t1.setOnClickListener {
            var intent = Intent(context, UpdateDeleteMeals::class.java)
            intent.putExtra("Meal_name", holder.t1.text)
            context.startActivity(intent)
        }

       holder.t3.setOnClickListener {
           var intent = Intent(context, statistics::class.java)
           intent.putExtra("Meal_name2", holder.t1.text)
           context.startActivity(intent)
       }
    }

    override fun getItemCount(): Int {
        return arr!!.size
    }
    inner class myholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var t1: TextView
        var t3: Button
        init {
            t1 = itemView.findViewById(R.id.t2)
            t3 = itemView.findViewById(R.id.addmeallist)
        }
    }
}

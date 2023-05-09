package com.example.finalproject2022r

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class ChefMealFragment : Fragment() {
    var recview: RecyclerView? = null
    var arr: ArrayList<model> = ArrayList()
    var db: FirebaseFirestore? = null
    var adapter: AdapterMeal2? = null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_chef_meal, container, false)
        recview = v.findViewById<View>(R.id.Recycle_menumeaals) as RecyclerView
        recview!!.layoutManager = LinearLayoutManager(context)
        adapter = AdapterMeal2(context!!, arr)
        recview!!.adapter = adapter
        gg()
        return v
    }
    private fun gg(){
       var id="Q0zcdgtDkdji8npaa7Iy"
        db = FirebaseFirestore.getInstance()
        db!!.collection("Resturents").document(id).collection("meal").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for(document in queryDocumentSnapshots) {
                    Log.d("ttt", "${document.id} ")
                }
                val list = queryDocumentSnapshots.documents
                for (d in list) {
                    val obj = d.toObject(model::class.java)
                    arr!!.add(obj!!)
                }
                adapter!!.notifyDataSetChanged()
            }
    }
}
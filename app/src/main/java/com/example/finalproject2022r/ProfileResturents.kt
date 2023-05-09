package com.example.finalproject2022r

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class ProfileResturents : AppCompatActivity() {
    var adapter: AdapterResturents? = null
    var recview: RecyclerView? = null
    var arr: ArrayList<model> = ArrayList()
    var db: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_resturents)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        recview = findViewById<View>(R.id.Recycle_menu_meal) as RecyclerView
        recview!!.layoutManager = LinearLayoutManager(applicationContext)
        adapter = AdapterResturents(applicationContext, arr)
       recview!!.adapter = adapter
       gg()
    }

    private fun gg(){
        var id=intent!!.getStringExtra("ide1")
        db = FirebaseFirestore.getInstance()
        db!!.collection("Resturents").document(id.toString()).collection("meal").get()
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
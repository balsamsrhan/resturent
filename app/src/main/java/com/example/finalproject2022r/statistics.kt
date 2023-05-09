package com.example.finalproject2022r

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_update_delete_meals.*
import java.util.ArrayList

class statistics : AppCompatActivity() {
    var recview: RecyclerView? = null
    var arr: ArrayList<model> = ArrayList()
    var db: FirebaseFirestore? = null
    var adapter: Adapterstatic? = null
    lateinit var auth: FirebaseAuth
    lateinit var path: String
    lateinit var DB: FirebaseFirestore
    lateinit var storage: FirebaseStorage
    var id = "Q0zcdgtDkdji8npaa7Iy"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        auth = Firebase.auth
        DB = Firebase.firestore
        storage = Firebase.storage
        recview = findViewById<View>(R.id.Recycle_menumallist) as RecyclerView
        recview!!.layoutManager = LinearLayoutManager(this)
        adapter = Adapterstatic(this, arr)
        recview!!.adapter = adapter
        gg()
    }
    private fun gg(){
        var id="Q0zcdgtDkdji8npaa7Iy"
        var idmea= "Phac3pFueuOnwyZoB8im"
        var m_name=intent!!.getStringExtra("Meal_name2")
        db = FirebaseFirestore.getInstance()
        db!!.collection("Resturents").document(id).collection("meal").whereEqualTo("name",m_name).get()
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
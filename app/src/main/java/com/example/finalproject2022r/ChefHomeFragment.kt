package com.example.finalproject2022r

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList


class ChefHomeFragment : Fragment() {
    var recview: RecyclerView? = null
    var arr: ArrayList<model> = ArrayList()
    var db: FirebaseFirestore? = null
    var adapter: myadapter? = null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_chef_home, container, false)
        recview = v.findViewById<View>(R.id.Recycle_menu) as RecyclerView
        recview!!.layoutManager = LinearLayoutManager(context)
        adapter = myadapter(context!!, arr)
        recview!!.adapter = adapter
       gg()
        return v
    }

private fun gg(){
    db = FirebaseFirestore.getInstance()
    db!!.collection("Resturents").get()
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
/*    private fun ggm(){
        db = FirebaseFirestore.getInstance()
        db!!.collection("Resturents").document(id.toString()).collection("meal").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for(document in queryDocumentSnapshots) {
                    Log.d("ttt", "${document.id} ")
                }
                val list = queryDocumentSnapshots.documents
                for (d in list) {
                    val obj = d.toObject(modelmeal::class.java)
                    arr2!!.add(obj!!)
                }
                adapter!!.notifyDataSetChanged()
            }
    }*/
}
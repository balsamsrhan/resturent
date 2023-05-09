package com.example.finalproject2022r

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_chef_meal.*
import kotlinx.android.synthetic.main.activity_update_delete_res.*
import java.util.*

class ChefMealAc : AppCompatActivity() , IPickResult {
    lateinit var auth: FirebaseAuth
    lateinit var DB: FirebaseFirestore
    lateinit var storage: FirebaseStorage
    lateinit var referance: StorageReference
    lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef_meal)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        auth = Firebase.auth
        DB = Firebase.firestore
        storage = Firebase.storage
        referance = storage.reference
        imageuploadmeal.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
        btnrat1meal.setOnClickListener {
            val rati = _ratinbtnmeal.getRating()
            tvmeal.setText("" + rati)
            if (_ratinbtnmeal.getRating() >= 3) {
            }
        }
        postmeal.setOnClickListener {
                addmeal(
            namemeal.editText!!.text.toString(),
  tvmeal.text.toString(),
    descrbtion.editText!!.text.toString(),
            pric.editText!!.text.toString(),
            )
        }
    /*    postmealup.setOnClickListener {
            var i = Intent(applicationContext,UpdatedeleteMeals::class.java)
            startActivity(i)
        }*/
    }

    override fun onPickResult(r: PickResult?) {

        imageuploadmeal.setImageBitmap(r!!.bitmap)
        upoladImage(r.uri)
    }

    private fun upoladImage(uri: Uri) {
        // progressDialoge!!.show()
        referance.child("profile/" + UUID.randomUUID().toString()).putFile(uri)
            .addOnSuccessListener { taskSnapshot ->
                //      progressDialoge!!.hide()
                Toast.makeText(this, "تم التحميل ", Toast.LENGTH_LONG).show()

                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    path = uri.toString()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "فشل التحميل ", Toast.LENGTH_LONG).show()
            }
    }

    private fun addmeal(
        name: String,
        rate: String,
        discription: String,
        price: String,
    ) {
        var id=intent!!.getStringExtra("ide")
        var meal =
            hashMapOf(
                "name" to name,
                "rate" to rate,
                "discription" to discription,
                "price" to price,
                "image" to path
            )
        DB!!.collection("Resturents").document(id.toString()).collection("meal").add(meal).addOnSuccessListener {
            Toast.makeText(this, "Add Meal Success", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { exception ->

        }
    }
}


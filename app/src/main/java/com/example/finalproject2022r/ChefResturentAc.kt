package com.example.finalproject2022r

import android.Manifest
import android.R.attr.button
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_chef_resturent.*
import java.util.*
import com.google.firebase.firestore.DocumentReference as D


class ChefResturentAc : AppCompatActivity(), IPickResult{
    lateinit var auth: FirebaseAuth
    lateinit var DB: FirebaseFirestore
    lateinit var storage: FirebaseStorage
    lateinit var referance: StorageReference
    lateinit var path: String
  //  lateinit var documentReference : DocumentReference

    //val user = auth.currentUser
   // var progressDialoge: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef_resturent)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
      //  progressDialoge = ProgressDialog(this)
     //  progressDialoge!!.setMessage("Image Is Loading ... plase wait...")
        DB = Firebase.firestore
        auth = Firebase.auth
        storage = Firebase.storage
        referance = storage.reference
         imageupload.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
      btnrat1.setOnClickListener(View.OnClickListener {
            //val total = "Total Start: " + _ratinbtn.getNumStars()
            val rati = _ratinbtn.getRating()
            tv.setText("" + rati)
            // Toast.makeText(applicationContext, "totalStars$total$rati", Toast.LENGTH_SHORT).show()
            if (_ratinbtn.getRating() >= 3) {
            }
        })
        post.setOnClickListener {
            addRes(
                auth.currentUser!!.uid,
                nameres.editText!!.text.toString(),
                addres.editText!!.text.toString(),
                tv.text.toString(),
                path
            )
        }
        location.setOnClickListener {
            getLastLocation()
        }
    }
    override fun onPickResult(r: PickResult?) {

        imageupload.setImageBitmap(r!!.bitmap)
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
    private fun addRes(
        id: String,
        name: String,
        address: String,
        rate:String,
        image: String

    ) {
        val usre = hashMapOf(
            "id" to id,
            "name" to name,
            "address" to address,
            "rate" to rate,
            "image" to image
        )
        DB.collection("Resturents").add(usre).addOnSuccessListener {

//            Log.e("bal","DocumentSnapshot add with id : ${documentReference.id}")
            Toast.makeText(this, "Add Successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Add is not Successfully", Toast.LENGTH_LONG).show()
        }
    }

    fun getLastLocation(){
        val locationclient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationclient.lastLocation
            .addOnSuccessListener { location->
if (locationclient!=null){
    val late = location.latitude
    val lon = location.longitude
    Log.d("bal",late.toString())
    Log.d("bal",lon.toString())
}
            }.addOnFailureListener{
              Log.e("bal","ErrorrLocation")
            }



    }
}


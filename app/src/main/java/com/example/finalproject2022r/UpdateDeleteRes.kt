package com.example.finalproject2022r

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_update_delete_meals.*
import kotlinx.android.synthetic.main.activity_update_delete_res.*
import kotlinx.android.synthetic.main.activity_update_delete_res.view.*
import java.util.*
import kotlin.collections.HashMap

class UpdateDeleteRes : AppCompatActivity(), IPickResult {
    lateinit var auth: FirebaseAuth
    lateinit var path: String
    lateinit var DB: FirebaseFirestore
    lateinit var storage: FirebaseStorage
    private lateinit var referance: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_res)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        auth = Firebase.auth
        DB = Firebase.firestore
        storage = Firebase.storage
        referance = storage.reference
        getData()
        _Updatedish1.setOnClickListener {
            updateResturents(_nameres1.editText!!.text.toString(),
                _addres1.editText!!.text.toString(), ed.text.toString())
        }
        _Deletedish1.setOnClickListener{
            var tv_namer=intent!!.getStringExtra("rest_name")
            DB!!.collection("Resturents").whereEqualTo("name",tv_namer).get()
                .addOnSuccessListener { querySnapshot ->
                    DB!!.collection("Resturents").document(querySnapshot.documents.get(0).id)
                        .delete()
                    Toast.makeText(this, "تم الحذف  ", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { exception ->

                }
        }
        _imageupload1.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
        _Meals.setOnClickListener {
            DB!!.collection("Resturents").whereEqualTo("name",_nameres1.editText!!.text.toString()).get()
                .addOnSuccessListener {
                    DB!!.collection("Resturents").document(it.documents.get(0).id)

                    var i=Intent(this,ChefMealAc::class.java)
                    i.putExtra("ide","${ it.documents.get(0).id}")
                    Toast.makeText(this,"${ it.documents.get(0).id}",Toast.LENGTH_SHORT).show()
                    startActivity(i)
                }
        }
       _show_Meals.setOnClickListener {
            DB!!.collection("Resturents").whereEqualTo("name", _nameres1.editText!!.text.toString()).get()
                .addOnSuccessListener {
                    DB!!.collection("Resturents").document(it.documents.get(0).id)

                    var i=Intent(this,ProfileResturents::class.java)
                    i.putExtra("ide1","${ it.documents.get(0).id}")
                    Toast.makeText(this,"${ it.documents.get(0).id}",Toast.LENGTH_SHORT).show()
                    startActivity(i)
                }
        }
    }
    private fun getData() {
        var tv_namer=intent!!.getStringExtra("rest_name")
        DB.collection("Resturents").whereEqualTo("name",tv_namer).get()
            .addOnSuccessListener { querySnapshot ->
                _nameres1.editText!!.setText(querySnapshot.documents!!.get(0).get("name").toString())
                _addres1.editText!!.setText(querySnapshot.documents!!.get(0).get("address").toString())
                ed.setText(querySnapshot.documents!!.get(0).get("rate").toString())
                Picasso.get().load(
                    querySnapshot.documents.get(0).get("image")
                        .toString()
                ).into(_imageupload1)

            }
    }

    private fun updateResturents(/*Id:String,*/name: String, address: String, rate: String) {
        val user = HashMap<String, Any>()
        user["name"] = name
        user["address"] = address
        user["rate"] = rate
        user["image"] = path
        DB.collection("Resturents").whereEqualTo("id",auth.currentUser!!.uid).get()
            .addOnSuccessListener { querySnapshot ->
                DB.collection("Resturents").document(/*Id*/ querySnapshot.documents.get(0).id)
                    .update(user)
                Toast.makeText(this, "تم التعديل  ", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Log.e("bb", "" + exception.message)
            }

    }
    override fun onPickResult(r: PickResult?) {

        _imageupload1.setImageBitmap(r!!.bitmap)
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
}
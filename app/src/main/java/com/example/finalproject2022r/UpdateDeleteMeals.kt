package com.example.finalproject2022r

import android.app.ProgressDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_chef_meal.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_delete_meals.*
import kotlinx.android.synthetic.main.activity_update_delete_res.*
import java.util.*
import kotlin.collections.HashMap

class UpdateDeleteMeals : AppCompatActivity() , IPickResult {
    lateinit var auth: FirebaseAuth
    lateinit var path: String
    lateinit var DB: FirebaseFirestore
    lateinit var storage: FirebaseStorage
    var id="Q0zcdgtDkdji8npaa7Iy"
    lateinit var progressDialog: ProgressDialog
    private lateinit var referance: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_meals)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("جاري التحميل")
        progressDialog.setCancelable(false)
        auth = Firebase.auth
        DB = Firebase.firestore
        storage = Firebase.storage
        referance = storage.reference
        getData()
        UpdateMeal.setOnClickListener {
            updateMeal(_namemeal.editText!!.text.toString(),_tvmeal.text.toString(),_descrbtion.editText!!.text.toString(),_pric.editText!!.text.toString())
        }
        deleteMeal.setOnClickListener {
            deleteMeal()
        }
        _imageuploadmeal.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
    }
    private fun getData() {

        var tv_namer=intent!!.getStringExtra("Meal_name")
        DB.collection("Resturents").document(id).collection("meal").whereEqualTo("name",tv_namer).get()
            .addOnSuccessListener { querySnapshot ->
                _namemeal.editText!!.setText(querySnapshot.documents!!.get(0).get("name").toString())
                _tvmeal.setText(querySnapshot.documents!!.get(0).get("rate").toString())
                _descrbtion.editText!!.setText(querySnapshot.documents!!.get(0).get("discription").toString())
                _pric.editText!!.setText(querySnapshot.documents!!.get(0).get("price").toString())
                Picasso.get().load(
                    querySnapshot.documents.get(0).get("image")
                        .toString()
                ).into(_imageuploadmeal)

            }
    }

    private fun updateMeal(/*Id:String,*/name: String, rate: String, discription: String,price:String) {
        var m_name=intent!!.getStringExtra("Meal_name")
        val meal = HashMap<String, Any>()
        meal["name"] = name
        meal["rate"] = rate
        meal["discription"] = discription
        meal["price"] = price
        meal["image"] = path
        var collection= DB.collection("Resturents").document(id).collection("meal")
        collection.whereEqualTo("name",m_name).get()
            .addOnSuccessListener { querySnapshot ->
              collection.document(querySnapshot.documents.get(0).id)
                    .update(meal)
                Toast.makeText(this, "تم التعديل  ", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Log.e("bb", "" + exception.message)
            }

    }
    private fun deleteMeal() {
        var m_name=intent!!.getStringExtra("Meal_name")
        var collection=DB!!.collection("Resturents").document(id).collection("meal")
        collection.whereEqualTo("name",m_name).get()
            .addOnSuccessListener { querySnapshot ->
                collection.document(querySnapshot.documents.get(0).id)
                    .delete()
                Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->

            }
    }
    override fun onPickResult(r: PickResult?) {

        _imageuploadmeal.setImageBitmap(r!!.bitmap)
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
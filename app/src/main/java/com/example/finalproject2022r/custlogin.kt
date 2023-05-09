package com.example.finalproject2022r

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chef_registration.*
import kotlinx.android.synthetic.main.activity_custlogin.*

class custlogin : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var DB: FirebaseFirestore
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custlogin)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        auth = Firebase.auth
        DB = Firebase.firestore
        __Signup.setOnClickListener {
            createNewUser(_Email.editText!!.text.toString(), _Pwd.editText!!.text.toString())
        }
        _email.setOnClickListener {
            val login= Intent(this, ChefLogin::class.java)
            startActivity(login)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun createNewUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "SignUp is Successfully", Toast.LENGTH_LONG).show()
                addUser(
                    user!!.uid,
                    _Firstname.editText!!.text.toString(),
                    _Lastname.editText!!.text.toString(),
                   _Email.editText!!.text.toString(),
                    _Pwd.editText!!.text.toString(),
                    _Mobileno.editText!!.text.toString(),
                )

            } else {
                Toast.makeText(this, "SignUp is Not Successfully", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun addUser(
        id: String,
        name: String,
        lname: String,
        email: String,
        pass: String,
        mobile:String
    ) {
        val usre = hashMapOf(
            "id" to id,
            "name" to name,
            "lname" to lname,
            "email" to email,
            "pass" to pass,
            "mobile" to mobile
        )
        DB.collection("chef").add(usre).addOnSuccessListener {
            // Log.e("bal","DocumentSnapshot add with id : ${documentReference.id}")
            Toast.makeText(this, "SignUp is Successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "SignUp is not Successfully", Toast.LENGTH_LONG).show()
        }
    }
}


package com.example.finalproject2022r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chef_login.*

class ChefLogin : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef_login)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        auth = Firebase.auth
        button4.setOnClickListener {
            signInNewUser(Lemail.editText!!.text.toString(), Lpassword.editText!!.text.toString())
        }
        btnphone.setOnClickListener {
            val login= Intent(this, Chefloginphone::class.java)
            startActivity(login)
        }
    }
    private fun signInNewUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this,ChefFoodPanel_BottomNavigation::class.java))
                Toast.makeText(this, "You are is logged", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "SignUp is not Successfully", Toast.LENGTH_LONG).show()
            }
        }
    }
}

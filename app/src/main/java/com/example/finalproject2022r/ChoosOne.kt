package com.example.finalproject2022r

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_choos_one.*

class ChoosOne : AppCompatActivity() {
    lateinit var type:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choos_one)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val animationDrawable = AnimationDrawable()
        animationDrawable.addFrame(resources.getDrawable(R.drawable.bg2), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img2), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img3), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img4), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img5), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img6), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img7), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img8), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.bg3), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img9), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img10), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img11), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.img11), 3000)


        back3.setBackgroundDrawable(animationDrawable)

        animationDrawable.start()

        intent = intent
        type = intent.getStringExtra("Home").toString().trim { it <= ' ' }

        chef.setOnClickListener(View.OnClickListener {
            if (type == "Email") {
                val loginemail = Intent(this, ChefLogin::class.java)
                startActivity(loginemail)
                // finish();
            }
            if (type == "Phone") {
                val loginphone = Intent(this, Chefloginphone::class.java)
                startActivity(loginphone)
                // finish();
            }
            if (type == "SignUp") {
                val Register = Intent(this, ChefRegistration::class.java)
                startActivity(Register)
            }
        })

       customer.setOnClickListener(View.OnClickListener {
         /*   if (type == "Email") {
                val loginemailcust = Intent(this, custlogin::class.java)
                startActivity(loginemailcust)
                // finish();
            }*/
            if (type == "SignUp") {
                val Registercust = Intent(this, custlogin::class.java)
                startActivity(Registercust)
            }
        })

    }
}

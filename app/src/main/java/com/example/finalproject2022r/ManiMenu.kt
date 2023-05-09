package com.example.finalproject2022r

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mani_menu.*

class ManiMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mani_menu)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin)
        val zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout)
        back2.setAnimation(zoomin)
        back2.setAnimation(zoomout)
        zoomout.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                back2.setAnimation(zoomin)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        SignwithEmail.setOnClickListener(View.OnClickListener {
            val email = Intent(this@ManiMenu, ChoosOne::class.java)
            email.putExtra("Home", "Email")
            startActivity(email)
            finish()
        })
        SignwithPhone.setOnClickListener(View.OnClickListener {
            val email = Intent(this@ManiMenu, ChoosOne::class.java)
            email.putExtra("Home", "Phone")
            startActivity(email)
            finish()
        })
        Signup.setOnClickListener(View.OnClickListener {
            val email = Intent(this@ManiMenu, ChoosOne::class.java)
            email.putExtra("Home", "SignUp")
            startActivity(email)
            finish()
        })
    }
}
package com.example.finalproject2022r

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
            imageView!!.animate().alpha(0f).duration = 0
            textView7!!.animate().alpha(0f).duration = 0
            imageView!!.animate().alpha(1f).setDuration(1000)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        textView7!!.animate().alpha(1f).duration = 800
                    }
                })
            Handler().postDelayed({
                val intent = Intent(this, ManiMenu::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}
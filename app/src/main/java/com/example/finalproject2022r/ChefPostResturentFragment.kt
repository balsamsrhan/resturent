package com.example.finalproject2022r

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class ChefPostResturentFragment : Fragment() {
    var post: Button? = null
    var bgimage: ConstraintLayout? = null


    fun ChefMealFragment() {
        // Required empty public constructor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_chef_post_resturent, container, false)
        requireActivity().title = "Add New Resturents"
        val animationDrawable = AnimationDrawable()
        animationDrawable.addFrame(resources.getDrawable(R.drawable.bghome2), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic2), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic3), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic5), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic6), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.bggg), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic9), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic10), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic11), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic12), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic13), 3000)
        animationDrawable.addFrame(resources.getDrawable(R.drawable.pic14), 3000)
        animationDrawable.isOneShot = false
        animationDrawable.setEnterFadeDuration(850)
        animationDrawable.setExitFadeDuration(1600)
        bgimage = v.findViewById(R.id.back2)
        bgimage!!.setBackgroundDrawable(animationDrawable)
        animationDrawable.start()
        post = v.findViewById<View>(R.id.post_res) as Button
        post!!.setOnClickListener { startActivity(Intent(context, ChefResturentAc::class.java)) }
        return v
    }
}
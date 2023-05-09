package com.example.finalproject2022r

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chef_food_panel_bottom_navigation.*

class ChefFoodPanel_BottomNavigation : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef_food_panel_bottom_navigation)
        chef_bottom_navigation.setOnNavigationItemSelectedListener(this)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        var name = intent.getStringExtra("PAGE")

        if (name != null) {
            if (name.equals("PastResturents", ignoreCase = true)) {
                loadcheffragment(ChefPostResturentFragment())
            } else if (name.equals("PastMeal", ignoreCase = true)) {
                loadcheffragment(ChefMealFragment())
            } else if (name.equals("Profile", ignoreCase = true)) {
                loadcheffragment(ChefProfileFragment())
            } else if (name.equals("Home", ignoreCase = true)) {
                loadcheffragment(ChefHomeFragment())
            }
        } else {
            loadcheffragment(ChefHomeFragment())
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.chefHome -> fragment = ChefHomeFragment()
            R.id.resturent -> fragment =
                ChefPostResturentFragment() //ChefPendingOrderFragment();
            R.id.meal -> fragment = ChefMealFragment()
            R.id.profil1 -> fragment = ChefProfileFragment()
        }
        return loadcheffragment(fragment)
    }

    private fun loadcheffragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment)
                .commit()
            return true
        }
        return false
    }
}

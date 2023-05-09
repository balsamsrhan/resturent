package com.example.finalproject2022r

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_chef_profile.*


class ChefProfileFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    lateinit var DB: FirebaseFirestore
    var post: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val v =  inflater.inflate(R.layout.fragment_chef_profile, container, false)
        auth = Firebase.auth
        DB = Firebase.firestore
        getUsreData()
        post = v.findViewById<View>(R.id.post_profile) as Button
        post!!.setOnClickListener {
            updateuser(editName.text.toString(),editLname.text.toString(),editEmail.text.toString(),editPhone.text.toString())
        }
        return v
    }

    private fun getUsreData() {
        DB.collection("chef").whereEqualTo("id", auth.currentUser!!.uid).get()
            .addOnSuccessListener { querySnapshot ->
                editName.setText(querySnapshot.documents!!.get(0).get("name").toString())
                editLname.setText(querySnapshot.documents!!.get(0).get("lname").toString())
                editEmail.setText(querySnapshot.documents!!.get(0).get("email").toString())
                editPhone.setText(querySnapshot.documents!!.get(0).get("mobile").toString())

            }
    }
    private fun updateuser(/*Id:String,*/name: String, lname: String, email: String, mobile: String) {
        val user = HashMap<String, Any>()
        user["name"] = name
        user["lname"] = lname
        user["email"] = email
        user["mobile"] = mobile
        //user["image"]=path
        DB.collection("chef").whereEqualTo("id", auth.currentUser!!.uid).get()
            .addOnSuccessListener { querySnapshot ->
                DB.collection("chef").document(/*Id*/ querySnapshot.documents.get(0).id)
                    .update(user)
                Toast.makeText(context,"تم التعديل",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Log.e("bb", "" + exception.message)
            }

    }
    }
package com.example.signupsigninapp_w6_d3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.signupsigninapp_w6_d3.databinding.ActivitySignUpBinding
import com.example.signupsigninapp_w6_d3.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var dbHelper: Database
    lateinit var name: String
    lateinit var loc: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        initializeBinding()
        getuserDetails()
    }

    private fun getuserDetails(){
        //retrieve details
        dbHelper = Database(this)
        name = intent.extras?.getString("Name").toString()
        loc = dbHelper.retrieveRowByName(name)
        //print details
        binding.tvName.text = "Name: " + name
        binding.tvLoc.text = "Location: " + loc
    }

    private lateinit var binding: ActivityUserDetailsBinding
    private fun initializeBinding() {
        Log.d("MAIN", "Binding Initialized!")
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun signOutButton(view: View) {
        //move to sign out > main
        moveToMain()
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


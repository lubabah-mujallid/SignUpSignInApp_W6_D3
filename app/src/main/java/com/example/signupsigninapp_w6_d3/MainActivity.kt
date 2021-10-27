package com.example.signupsigninapp_w6_d3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.signupsigninapp_w6_d3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signInButton(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun signUpButton(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}

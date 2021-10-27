package com.example.signupsigninapp_w6_d3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.signupsigninapp_w6_d3.databinding.ActivitySignInBinding
import com.example.signupsigninapp_w6_d3.databinding.ActivitySignUpBinding

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initializeBinding()
        dbHelper = Database(this)
    }

    fun SignInButton(view: View) {
        checkUser()
    }

    private fun getUserDetails(): User {
        val mobile = binding.etMobile.text.toString().toLong()
        val pass = binding.etPass.text.toString()
        return User(0, "", mobile, "", pass)
    }

    private lateinit var dbHelper: Database
    private fun checkUser() {
        val user = getUserDetails()
        val data = dbHelper.isRealUser(user.mobile.toString(), user.password)
        if (data == 0) {
            binding.etPass.text.clear()
            Toast.makeText(this, "Password is Incorrect!", Toast.LENGTH_LONG).show()
        }
        else if (data == -1) {
            binding.etMobile.text.clear()
            Toast.makeText(this, "Name is Incorrect!", Toast.LENGTH_LONG).show()
        }
        else {
            moveToDetailsActivity(data.toString())
        }
    }

    private fun moveToDetailsActivity(name: String){
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("Name", name)
        startActivity(intent)
    }

    fun moveToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private lateinit var binding: ActivitySignInBinding
    private fun initializeBinding() {
        Log.d("MAIN", "Binding Initialized!")
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
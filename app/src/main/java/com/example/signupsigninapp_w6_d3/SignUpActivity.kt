package com.example.signupsigninapp_w6_d3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.signupsigninapp_w6_d3.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initializeBinding()
    }

    private fun getNewUserDetails(): User {
        name = binding.etName.text.toString()
        val mobile = binding.etMobile.text.toString().toLong()
        val loc = binding.etLocation.text.toString()
        val pass = binding.etPass.text.toString()
        binding.etName.text.clear()
        binding.etMobile.text.clear()
        binding.etLocation.text.clear()
        binding.etPass.text.clear()
        return User(0, name, mobile, loc, pass)
    }

    private lateinit var dbHelper: Database
    private fun addUserToDatabase(){
        //save data to Database
        dbHelper = Database(this)
        dbHelper.saveData(getNewUserDetails())
        Log.d("MAIN", "new data added")
        Toast.makeText(this,"Sign Up Successful!!", Toast.LENGTH_LONG)
    }

    private lateinit var binding: ActivitySignUpBinding
    private fun initializeBinding() {
        Log.d("MAIN", "Binding Initialized!")
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun signUpButton(view: View) {
        //add info to database
        addUserToDatabase()
        //move to details activity
        moveToDetailsActivity()
    }

    private fun moveToDetailsActivity(){
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("Name", name)
        startActivity(intent)
    }

    fun moveToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

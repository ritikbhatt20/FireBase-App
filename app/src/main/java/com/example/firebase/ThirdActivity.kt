package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val email=intent.getStringExtra(SignInActivity.KEY1)
        val password=intent.getStringExtra(SignInActivity.KEY2)

        val welcomeText=findViewById<TextView>(R.id.tVWelcome)
        val mailText=findViewById<TextView>(R.id.tVMail)
        val passwordText=findViewById<TextView>(R.id.tVPassword)

        welcomeText.text="Welcome $email"
        mailText.text="Mail : $email"
        passwordText.text="Password : $password"

        val btnBack=findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent= Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
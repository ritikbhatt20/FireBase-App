package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SecondActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val signButton=findViewById<Button>(R.id.btnSignUp)
        val etMail=findViewById<TextInputEditText>(R.id.etMail)
        val etPassword=findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {

            val mail=etMail.text.toString()
            val password=etPassword.text.toString()

            val user=User(mail,password)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(mail).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

        val btnSignUp=findViewById<Button>(R.id.button1)
        btnSignUp.setOnClickListener {
            val intent=Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
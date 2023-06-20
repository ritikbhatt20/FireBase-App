package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1="com.example.firebase.SignInActivity.mail"
        const val KEY2="com.example.firebase.SignInActivity.password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton=findViewById<Button>(R.id.btnSignIn)
        val email=findViewById<TextInputEditText>(R.id.etMail)
        val password=findViewById<TextInputEditText>(R.id.etPassword)

        signInButton.setOnClickListener {

            val mail=email.text.toString()
            val password=password.text.toString()

            if(mail.isNotEmpty()){
                readData(mail)
            }
            else{
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            }
        }
        val btnCreate=findViewById<Button>(R.id.button1)
        btnCreate.setOnClickListener {
            val intent=Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }


    private fun readData(mail: String) {
         databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(mail).get().addOnSuccessListener {

            if(it.exists()){
                val email=it.child("email").value
                val password=it.child("password").value

                val intent=Intent(this, ThirdActivity::class.java)
                intent.putExtra(KEY1, email.toString())
                intent.putExtra(KEY2, password.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"User does not exist, Please Sign Up",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}
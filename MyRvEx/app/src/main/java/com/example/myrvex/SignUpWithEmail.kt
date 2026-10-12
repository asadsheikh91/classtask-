package com.example.myrvex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpWithEmail : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val databaseUrl = "https://smd-lecture-55391-default-rtdb.asia-southeast1.firebasedatabase.app"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_with_email)

        auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance(databaseUrl)

        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvGoToSignIn = findViewById<TextView>(R.id.tvGoToSignIn)

        btnSignUp.setOnClickListener {
            val nameText = name.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (nameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val userMap = HashMap<String, String>()
                userMap["name"] = nameText
                userMap["email"] = emailText
                userMap["password"] = passwordText
                database.reference.child("users").push().setValue(userMap)

                auth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Sign Up recorded in database", Toast.LENGTH_SHORT).show()
                        }
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
            }
        }

        tvGoToSignIn.setOnClickListener {
            val intent = Intent(this, SignInWithEmail::class.java)
            startActivity(intent)
        }
    }
}
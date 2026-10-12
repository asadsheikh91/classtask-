package com.example.myrvex

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private val databaseUrl = "https://smd-lecture-55391-default-rtdb.asia-southeast1.firebasedatabase.app"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = FirebaseDatabase.getInstance(databaseUrl)

        setContentView(R.layout.activity_add)
        val name = findViewById<EditText>(R.id.name)
        val phoneNumber = findViewById<EditText>(R.id.phno)
        val save = findViewById<Button>(R.id.save)

        save.setOnClickListener {
            val c = Contact(
                1,
                " ",
                name.text.toString(),
                phoneNumber.text.toString()
            )
            val ref = database.reference.child("contacts").push()
            ref.setValue(c).addOnSuccessListener {
                finish()
            }
        }
    }
}
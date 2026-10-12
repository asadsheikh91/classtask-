package com.example.myrvex

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val databaseUrl = "https://smd-lecture-55391-default-rtdb.asia-southeast1.firebasedatabase.app"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = FirebaseDatabase.getInstance(databaseUrl)

        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv)
        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val ls = ArrayList<Contact>()
        val ref = database.reference.child("contacts")
        ref.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val c = snapshot.getValue(Contact::class.java)
                if (c != null) {
                    ls.add(c)
                    val adapter = MyAdapter(ls, this@MainActivity)
                    rv.adapter = adapter
                    val lm = LinearLayoutManager(this@MainActivity)
                    rv.layoutManager = lm
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
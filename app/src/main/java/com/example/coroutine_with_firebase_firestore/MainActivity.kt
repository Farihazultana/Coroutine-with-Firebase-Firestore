package com.example.coroutine_with_firebase_firestore

import android.app.Person
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tutorialDocument= Firebase.firestore
            .collection("coroutines")
            .document("tutorial")

        val peter = Person("Peter",24)

        GlobalScope.launch(Dispatchers.IO) {
            //delay(3000L)
            //tutorialDocument.set(peter).await()

            withContext(Dispatchers.Main){
                tvData.text = tutorialDocument.get().await().toObject(Person::class.java).toString()
            }
        }
    }
}
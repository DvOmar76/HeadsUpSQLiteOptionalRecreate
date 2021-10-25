package com.example.headsupsqliteoptionalrecreate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.headsupsqliteoptionalrecreate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper:DBHelper

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper=DBHelper(applicationContext)
    }

    fun start(view: android.view.View) {
        if(dbHelper.getData().isNotEmpty()){
            startActivity(Intent(applicationContext,HeadsUpGame::class.java))
        }else{
            Toast.makeText(applicationContext, "the  database is empty", Toast.LENGTH_SHORT).show()
        }
    }
    fun addNew(view: android.view.View) {
        startActivity(Intent(applicationContext,AddNewCelebrities::class.java))

    }
}
package com.example.headsupsqliteoptionalrecreate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.headsupsqliteoptionalrecreate.databinding.ActivityAddNewCelebritiesBinding

class AddNewCelebrities : AppCompatActivity() {
    lateinit var binding: ActivityAddNewCelebritiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNewCelebritiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper=DBHelper(applicationContext)
        binding.btnAdd.setOnClickListener {
            val name=binding.edName.text.toString()
            val taboo1=binding.edTaboo1.text.toString()
            val taboo2=binding.edTaboo2.text.toString()
            val taboo3=binding.edTaboo3.text.toString()
            if (name.isNotEmpty()&&taboo1.isNotEmpty()&&taboo2.isNotEmpty()&&taboo3.isNotEmpty())
            {
                val status=dbHelper.addData(name,taboo1,taboo2,taboo3)
                if (status!=-1L){
                    Toast.makeText(applicationContext, "Done $status", Toast.LENGTH_SHORT).show()
                    binding.edName.text.clear()
                    binding.edTaboo1.text.clear()
                    binding.edTaboo2.text.clear()
                    binding.edTaboo3.text.clear()
                    Log.d("asdffsda561",dbHelper.getData().toString())
                }
            }
            else
            {
                Toast.makeText(applicationContext, "some filed is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
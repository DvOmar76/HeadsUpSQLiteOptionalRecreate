package com.example.headsupsqliteoptionalrecreate

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.headsupsqliteoptionalrecreate.databinding.ActivityHeadsUpGameBinding
import com.example.headsupsqliteoptionalrecreate.databinding.RotateBinding
import kotlin.random.Random

class HeadsUpGame : AppCompatActivity() {
    lateinit var binding: ActivityHeadsUpGameBinding
    lateinit var rotateBinding: RotateBinding
    lateinit var dbHelper: DBHelper
    private  var gameActive:Boolean = false
    private  var list=ArrayList<Celebrity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHeadsUpGameBinding.inflate(layoutInflater)
        rotateBinding= RotateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper=DBHelper(applicationContext)
        list=dbHelper.getData()

        dbHelper= DBHelper(applicationContext)
        newTimer()
        Log.d("asdf55asd",list.toString())


    }

    override fun onConfigurationChanged(newConfig: Configuration)
    {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
               setContentView(rotateBinding.root)
               fetchData()
               gameActive=true
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(binding.root)
        }
    }


    private fun fetchData() {
        var randomIndex= Random.nextInt(list.size)
        rotateBinding.tvShow.setText("${list[randomIndex].name}")
        print(randomIndex)
        var text="${list[randomIndex].taboo1}\n${list[randomIndex].taboo2}\n${list[randomIndex].taboo3}\n"
        rotateBinding.tvTaboo.setText(text)
    }

    private fun newTimer(){
        if(!gameActive){
            gameActive = true
            object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvTime.text = "Time: ${millisUntilFinished / 1000}"
                    rotateBinding.tvTime.text = "Time: ${millisUntilFinished / 1000}"
                }
                override fun onFinish() {
                    gameActive = false
                    intent= Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
            }.start()
        }
    }
}
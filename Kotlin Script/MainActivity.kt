package com.halac123b.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val inputName: EditText = findViewById(R.id.inputName)
        btnStart.setOnClickListener{
            if (inputName.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }
            else {
                val intentQuiz = Intent(this, QuizActivity::class.java)
                intentQuiz.putExtra(Constant.USER_NAME, inputName.text.toString())
                startActivity(intentQuiz)
            }
        }
    }
}
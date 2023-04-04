package com.halac123b.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName: TextView = findViewById(R.id.tv_name)
        val score: TextView = findViewById(R.id.tv_score)
        val finishBtn: Button = findViewById(R.id.btn_finish)

        userName.text = intent.getStringExtra(Constant.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constant.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constant.CORRECT_ANSWERS, 0)

        score.text = "Your Score is $correctAnswers out of $totalQuestions."

        finishBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
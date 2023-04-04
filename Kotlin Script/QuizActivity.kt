package com.halac123b.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() , View.OnClickListener {
    private var userName: String? = null
    private var progressBar: ProgressBar? = null
    private var textProgress: TextView? = null
    private var textQuestion: TextView? = null
    private var imageQuestion: ImageView? = null

    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private var submitBtn: Button? = null

    private var questionIndex: Int = 1
    private var questionList: ArrayList<Question>? = null

    private var selectedIndex: Int = 0
    private var correctAnswerNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        userName = intent.getStringExtra(Constant.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        textProgress = findViewById(R.id.textProgress)
        textQuestion = findViewById(R.id.question)
        imageQuestion = findViewById(R.id.questionImg)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)
        submitBtn = findViewById(R.id.submitBtn)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        questionList = Constant.getQuestion()

        setQuestion()
    }

    private fun setQuestion(){
        defaultOptionView()
        val question: Question = questionList!![questionIndex - 1]
        progressBar?.progress = questionIndex
        textProgress?.text = "$questionIndex/${progressBar?.max}"
        textQuestion?.text = question.question
        imageQuestion?.setImageResource(question.image)

        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

        if (questionIndex == questionList!!.size){
            submitBtn?.text = "FINISH"
        }
        else {
            submitBtn?.text = "SUBMIT"
        }
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        optionOne?.let {
            options.add(0, it)
        }
        optionTwo?.let {
            options.add(1, it)
        }
        optionThree?.let {
            options.add(2, it)
        }
        optionFour?.let {
            options.add(3, it)
        }
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.selectborder)
        }
    }

    private fun selectedOptionView(text: TextView, selectedNumber: Int){
        defaultOptionView()
        selectedIndex = selectedNumber
        text.setTextColor(Color.parseColor("#363A43"))
        text.setTypeface(text.typeface, Typeface.BOLD)
        text.background = ContextCompat.getDrawable(this, R.drawable.currentselect)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.optionOne -> {
                optionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.optionTwo -> {
                optionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.optionThree -> {
                optionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.optionFour -> {
                optionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.submitBtn -> {
                if (selectedIndex == 0){
                    questionIndex++
                    if (questionIndex <= questionList!!.size){
                        setQuestion()
                    }
                    else {
                        val intentResult = Intent(this, ResultActivity::class.java)
                        intentResult.putExtra(Constant.USER_NAME, userName)
                        intentResult.putExtra(Constant.CORRECT_ANSWERS, correctAnswerNum)
                        intentResult.putExtra(Constant.TOTAL_QUESTIONS, questionList?.size)
                        startActivity(intentResult)
                        finish()
                    }
                }
                else {
                    val currentQuestion = questionList?.get(questionIndex - 1)
                    if (selectedIndex != currentQuestion!!.correctAnswer){
                        answerView(selectedIndex, R.drawable.wrongoption)
                    }
                    else {
                        correctAnswerNum++
                    }
                    answerView(currentQuestion.correctAnswer, R.drawable.correctoption)

                    if (questionIndex == questionList!!.size){
                        submitBtn?.text = "FINISH"
                    }
                    else {
                        submitBtn?.text = "GO TO NEXT QUESTION"
                    }
                    selectedIndex = 0
                }
            }
        }
    }

    private fun answerView(answerIndex: Int, background: Int){
        when(answerIndex){
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(this, background)
            }
            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(this, background)
            }
            3 -> {
                optionThree?.background = ContextCompat.getDrawable(this, background)
            }
            4 -> {
                optionFour?.background = ContextCompat.getDrawable(this, background)
            }
        }
    }
}
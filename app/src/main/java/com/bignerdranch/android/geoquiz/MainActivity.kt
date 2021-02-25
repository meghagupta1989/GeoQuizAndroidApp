package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    private lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing view elements
        val trueButton:Button = findViewById(R.id.true_button)
        val falseButton: Button = findViewById(R.id.false_button)
        val nextButton: Button = findViewById(R.id.next_button)
        val prevButton: Button = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        //Displaying first question on screen
        updateQuestion()

        //Challenge 2.1 - Adding listener to questionTextView - if clicked on question, it will move to next question
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex+1) % questionBank.size
            updateQuestion()
        }

        //Next Button - move to next question
        nextButton.setOnClickListener{
            currentIndex = (currentIndex+1) % questionBank.size
            updateQuestion()
        }

        //Challenge 2.2 - Adding a previous button - move to previous question
        prevButton.setOnClickListener{
            currentIndex = if (currentIndex > 0) {
                currentIndex - 1
            } else {
                questionBank.size - 1
            }
            updateQuestion()
        }

        //Checking answers
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }
    }

    private fun updateQuestion() {
        questionTextView.setText(questionBank[currentIndex].textResId)
    }

    private fun checkAnswer(userAnswer : Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageId = if(userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.TOP, 0, 200)
            show()
        }
    }
}
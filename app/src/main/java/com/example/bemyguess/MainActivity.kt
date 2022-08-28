package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

//    var answer : Int = 0
    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }

    fun generateAnswer() {

        answer = Random.nextInt(1, 25)


    }

    fun startOver() {
        isGameOver = false
        generateAnswer()
        numOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()
    }

    fun btnStartOverTapped(view: View) {
        startOver()
    }

    fun btnSubmitTapped(view: View) {

        val guess = getUsersGuess() ?: -999
        val textView=findViewById<TextView>(R.id.textView)
//        if (guess < 1 || guess > 25)
        if (guess !in 1..25) { //not in 1 to 25
            textView.text = "Guess must be 1 to 25"
            return
        }

        var message = ""
        numOfAttempts++

        if (guess == answer) {
            message = "Correct! Guess(es): $numOfAttempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false
        }
        else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }

        textView.text = message

    }

    fun getUsersGuess() : Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val usersGuess = editTextGuess.text.toString()

        var guessAsInt = 0

        try{

            guessAsInt = usersGuess.toInt()
        }
        catch(e: Exception) {
            return null
        }

        return guessAsInt
    }
}
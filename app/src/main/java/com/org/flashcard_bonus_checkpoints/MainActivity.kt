package com.org.flashcard_bonus_checkpoints

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var areChoicesVisible = true
    private lateinit var allAnswers: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toggleIcon = findViewById<ImageView>(R.id.toggle_choices_visibility)
        val fa1 = findViewById<TextView>(R.id.flashcard_answer1)
        val fa2 = findViewById<TextView>(R.id.flashcard_answer2)
        val fa3 = findViewById<TextView>(R.id.flashcard_answer3)
        val question = findViewById<TextView>(R.id.flashcard_question)
        allAnswers = listOf(fa1, fa2, fa3)

        val ca = fa3
        val wa = listOf(fa1, fa2)

//        val defaultColor = ContextCompat.getColor(this, R.color.Light_Orange)
        val greenColor = ContextCompat.getColor(this, android.R.color.holo_green_light)
        val redColor = ContextCompat.getColor(this, android.R.color.holo_red_light)



        for (answer in allAnswers) {
            answer.setOnClickListener {

                allAnswers.forEach { it.isClickable = false }
                if (answer == ca) {
                    answer.setBackgroundColor(greenColor)
                } else {
                    answer.setBackgroundColor(redColor)
                    ca.setBackgroundColor(greenColor)
                }
            }
        }

        toggleIcon.setOnClickListener {
            if (areChoicesVisible) {
                fa1.visibility = TextView.INVISIBLE
                fa2.visibility = TextView.INVISIBLE
                fa3.visibility = TextView.INVISIBLE
                toggleIcon.setImageResource(R.drawable.show_icon)
                areChoicesVisible = false
            } else {
                fa1.visibility = TextView.VISIBLE
                fa2.visibility = TextView.VISIBLE
                fa3.visibility = TextView.VISIBLE
                toggleIcon.setImageResource(R.drawable.hide_icon)
                areChoicesVisible = true
            }
        }
        question.setOnClickListener {
            resetAnswers()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun resetAnswers() {
        val defaultColor = ContextCompat.getColor(this, R.color.Light_Orange)

        for (answer in allAnswers) {
            answer.setBackgroundColor(defaultColor)
            answer.isClickable = true
            answer.visibility = TextView.VISIBLE
        }
        areChoicesVisible = true
    }
}



package com.canadore.abin_benny_A00192794

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddUpdateHabitActivity : AppCompatActivity() {
    private lateinit var habitEditText: EditText
    private lateinit var timePicker: TimePicker
    private lateinit var daySpinner: Spinner
    private lateinit var saveButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_habit)

        habitEditText = findViewById(R.id.habitEditText)
        timePicker = findViewById(R.id.timePicker)
        daySpinner = findViewById(R.id.daySpinner)
        saveButton = findViewById(R.id.saveButton)

        databaseHelper = DatabaseHelper(this)

        saveButton.setOnClickListener {
            val habit = habitEditText.text.toString()
            val hour = timePicker.hour
            val minute = timePicker.minute
            val selectedDay = daySpinner.selectedItem.toString()

            if (habit.isNotEmpty()) {
                val time = String.format("%02d:%02d", hour, minute)
                databaseHelper.addHabit(habit, time, selectedDay) // Save habit to the database

                Toast.makeText(this, "Habit Saved: $habit at $time on $selectedDay", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK, Intent()) // Return to DetailActivity
                finish() // Close the activity
            } else {
                Toast.makeText(this, "Please enter a habit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

package com.canadore.abin_benny_A00192794

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class DetailActivity : AppCompatActivity() {
    private lateinit var habitListView: ListView
    private lateinit var addButton: Button
    private lateinit var databaseHelper: DatabaseHelper
    private val habits = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        habitListView = findViewById(R.id.habitListView)
        addButton = findViewById(R.id.addHabitButton)

        databaseHelper = DatabaseHelper(this)

        // Load habits from the database
        loadHabits()

        addButton.setOnClickListener {
            val intent = Intent(this, AddUpdateHabitActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_HABIT)
        }
    }

    private fun loadHabits() {
        habits.clear()
        val allHabits = databaseHelper.getAllHabits()
        allHabits.forEach { habit ->
            habits.add("${habit.habit} at ${habit.time} on ${habit.day}")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, habits)
        habitListView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_HABIT && resultCode == RESULT_OK) {
            loadHabits() // Refresh the list of habits
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_HABIT = 1
    }
}

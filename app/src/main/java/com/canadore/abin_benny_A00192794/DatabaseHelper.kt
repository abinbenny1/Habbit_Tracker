package com.canadore.abin_benny_A00192794

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_HABIT TEXT, $COLUMN_TIME TEXT, $COLUMN_DAY TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addHabit(habit: String, time: String, day: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_HABIT, habit)
            put(COLUMN_TIME, time)
            put(COLUMN_DAY, day)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllHabits(): List<Habit> {
        val habits = mutableListOf<Habit>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val habit = Habit(
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    habit = cursor.getString(cursor.getColumnIndex(COLUMN_HABIT)),
                    time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME)),
                    day = cursor.getString(cursor.getColumnIndex(COLUMN_DAY))
                )
                habits.add(habit)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return habits
    }

    companion object {
        private const val DATABASE_NAME = "habits.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "habits"
        private const val COLUMN_ID = "id"
        private const val COLUMN_HABIT = "habit"
        private const val COLUMN_TIME = "time"
        private const val COLUMN_DAY = "day"
    }
}

data class Habit(val id: Int, val habit: String, val time: String, val day: String)

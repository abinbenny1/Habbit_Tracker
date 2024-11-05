package com.canadore.abin_benny_A00192794

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val button = findViewById<Button>(R.id.launchButton)
        button.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
            finish() // Close Splash Activity
        }
    }
}

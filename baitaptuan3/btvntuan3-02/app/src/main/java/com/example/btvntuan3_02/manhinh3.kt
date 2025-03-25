package com.example.btvntuan3_02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class manhinh3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manhinh3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // chuyển sang màn hình 4
        val btt2 = findViewById<Button>(R.id.btt3)
        btt2.setOnClickListener {
            val intent = Intent(this, manhinh4::class.java)
            startActivity(intent)
        }
        // lùi về màn hình 2
        val ibt1 = findViewById<ImageButton>(R.id.ibt2)
        ibt1.setOnClickListener {
            val intent = Intent(this, manhinh2::class.java)
            startActivity(intent)
        }

    }
}
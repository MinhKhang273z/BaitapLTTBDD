package com.example.btvntuan3_02

import android.content.Intent
import android.os.Bundle
import android.os.Handler //Dùng để trì hoãn thực thi mã trong một khoảng thời gian.
import android.os.Looper  //Dùng để trì hoãn thực thi mã trong một khoảng thời gian.
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Chờ 3 giây rồi chuyển sang màn hình 2
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, manhinh2::class.java)
            startActivity(intent)
            finish() // bắt đầu chuyển sang màn hình
        }, 3000) //code bên trong sau 3 giây
    }
}

package com.example.kotlinstarted.activitylifecycletest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstarted.MainActivity
import com.example.kotlinstarted.R
import kotlinx.android.synthetic.main.activity_normal.*

class NormalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)
    }
}
package com.example.kotlinstarted.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstarted.R

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}
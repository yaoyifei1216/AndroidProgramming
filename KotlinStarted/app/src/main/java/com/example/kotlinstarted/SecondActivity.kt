package com.example.kotlinstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extraData = intent.getStringExtra("extra_data")
        button2.text = extraData
        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Hello MainActivity")
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}
package com.example.kotlinstarted

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extraData = intent.getStringExtra("extra_data")
        button2.text = extraData
        button2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            //intent.putExtra("data_return", "Hello MainActivity")
            //setResult(RESULT_OK,intent)
            //finish()
            startActivity(intent)
        }
    }

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
}
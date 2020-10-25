package com.example.kotlinstarted

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private val tag = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, javaClass.simpleName)
        ActivityCollector.addActivity(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy: ")
        ActivityCollector.removeActivity(this)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart: ")
    }
}
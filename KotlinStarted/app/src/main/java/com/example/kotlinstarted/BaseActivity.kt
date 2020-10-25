package com.example.kotlinstarted

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private val tag = "BaseActivity"
    lateinit var receiver: ForceOfflineReceiver

    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context != null) {
                AlertDialog.Builder(context).apply {
                    setTitle("Warning")
                    setMessage("You are forced to be offline")
                    setCancelable(false)
                    setPositiveButton("OK") { _, _ ->
                        ActivityCollector.finishAllActivity()
                        android.os.Process.killProcess(android.os.Process.myPid())
                    }.show()
                }
            }
        }
    }

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
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.kotlinstarted.broadcasttest.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: ")
        unregisterReceiver(receiver)
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
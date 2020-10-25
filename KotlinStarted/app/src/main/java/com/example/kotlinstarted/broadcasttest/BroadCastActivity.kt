package com.example.kotlinstarted.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ResultReceiver
import android.widget.Toast
import com.example.kotlinstarted.BaseActivity
import com.example.kotlinstarted.R
import kotlinx.android.synthetic.main.activity_board_cast.*

class BroadCastActivity : BaseActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_cast)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
        sendBroadcastButton.setOnClickListener {
            val intent = Intent("com.example.kotlinstarted.broadcasttest.MY_BROADCAST")
            intent.setPackage(packageName)
            sendOrderedBroadcast(intent, null)
        }
        forceOffline.setOnClickListener {
            val intent = Intent("com.example.kotlinstarted.broadcasttest.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}
package com.example.kotlinstarted

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.kotlinstarted.activitylifecycletest.DialogActivity
import com.example.kotlinstarted.activitylifecycletest.NormalActivity
import com.example.kotlinstarted.fragmenttest.FragmentActivity
import com.example.kotlinstarted.recyclerviewtest.RecyclerViewTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate: ")
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            //val intent1 = Intent(Intent.ACTION_VIEW)
//            //intent1.data = Uri.parse("https://yaoyifei1216.github.io/")
//            val data = "startThirdActivity"
//            intent.putExtra("extra_data", data)
//            startActivity(intent)
//            //startActivityForResult(intent, 1216)
            SecondActivity.actionStart(this, "data1", "data2")
        }
        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener {
            ActivityCollector.finishAllActivity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        recyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerViewTestActivity::class.java)
            startActivity(intent)
        }
        fragment.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1216 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data_return")
                button1.text = returnData
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked add item", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked remove item", Toast.LENGTH_SHORT)
                .show()
        }
        return true
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
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart: ")
    }
}
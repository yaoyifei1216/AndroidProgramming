package com.example.kotlinstarted

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val intent1 = Intent(Intent.ACTION_VIEW)
            intent1.data = Uri.parse("https://yaoyifei1216.github.io/")
            val data = "Hello secondActivity"
            intent.putExtra("extra_data", data)
            //startActivity(intent)
            startActivityForResult(intent,1216)
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
            R.id.remove_item -> Toast.makeText(this, "You clicked remove item", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
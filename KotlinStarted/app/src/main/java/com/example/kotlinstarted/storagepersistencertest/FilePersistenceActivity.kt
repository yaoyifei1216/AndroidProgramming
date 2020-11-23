package com.example.kotlinstarted.storagepersistencertest

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstarted.R
import kotlinx.android.synthetic.main.activity_file_persistence.*
import java.io.*

class FilePersistenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistence)
        val inputText = load()
        if (inputText.isNotEmpty()) {
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "feige")
            editor.apply()
        }
        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            //Log.d("yaoyifei", "$name")
            Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()
        }
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)//创建数据库
        val db = dbHelper.writableDatabase //创建的数据库
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        addData.setOnClickListener {
            val values1 = ContentValues().apply {
                put("name", "java")
                put("author", "feige")
                put("price", 12.9)
                put("pages", 100)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "android")
                put("author", "yaoyifei")
                put("price", 13.8)
                put("pages", 200)
            }
            db.insert("Book", null, values2)
        }
        updateData.setOnClickListener {
            val values = ContentValues()
            values.put("price", 9.9)
            db.update("Book", values, "name = ?", arrayOf("java"))
        }
        deleteData.setOnClickListener {
            db.delete("Book", "pages > ?", arrayOf("150"))
        }
        queryData.setOnClickListener {
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    editText.setText("name is:$name,author is:$author,pages is:$pages,price is:$price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = editText.text.toString()
        save(inputText)
    }

    private fun save(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
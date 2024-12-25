package com.example.filemanager


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class fileContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_content)

        val filePath = intent.getStringExtra("filePath")
        val fileContent = File(filePath!!).readText()

        val textView: TextView = findViewById(R.id.textView)
        textView.text = fileContent
    }
}
package com.example.filemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.filemanagerapp.ui.theme.FileManagerAppTheme
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkAndRequestPermissions()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val root = Environment.getExternalStorageDirectory()
        displayFiles(root)
    }

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 100)
        }
    }

    private fun displayFiles(directory: File) {
        val files = directory.listFiles()?.toList() ?: emptyList()
        recyclerView.adapter = FileAdapter(files) { file ->
            if (file.isDirectory) {
                displayFiles(file)
            } else {
                openFile(file)
            }
        }
    }

    private fun openFile(file: File) {
        if (file.extension == "txt") {
            val intent = Intent(this, FileContentActivity::class.java)
            intent.putExtra("filePath", file.absolutePath)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Không thể mở file này!", Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FileManagerAppTheme {
        Greeting("Android")
    }
}
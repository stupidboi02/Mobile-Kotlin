package com.example.studentmanager_room
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.studentmanager_room.viewmodel.StudentViewModel
import com.example.studentmanager_room.database.Student

class MainActivity : AppCompatActivity() {
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentViewModel.allStudents.observe(this, Observer { students ->
            // Hiển thị danh sách sinh viên (thay thế UI thực tế ở đây)
            println("Danh sách sinh viên: $students")
        })
    }
}

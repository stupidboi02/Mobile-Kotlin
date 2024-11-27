package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddEditStudentActivity : AppCompatActivity() {
    private lateinit var edtId: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtId = findViewById(R.id.edtStudentId)
        edtName = findViewById(R.id.edtStudentName)
        btnSave = findViewById(R.id.btnSave)

        // Lấy dữ liệu từ Intent (nếu chỉnh sửa)
        val studentId = intent.getStringExtra("student_id")
        val studentName = intent.getStringExtra("student_name")

        if (studentId != null && studentName != null) {
            edtId.setText(studentId)
            edtName.setText(studentName)
        }

        // Xử lý khi nhấn nút Lưu
        btnSave.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("student_id", edtId.text.toString().trim())
            resultIntent.putExtra("student_name", edtName.text.toString().trim())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
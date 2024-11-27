package com.example.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<StudentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_students)

        // Gọi constructor của StudentAdapter và truyền các hàm callback cho Edit và Delete
        studentAdapter = StudentAdapter(studentList, { student ->
            // Hiển thị Dialog khi nhấn nút Edit
            showEditStudentDialog(student)
        }, { student ->
            // Hiển thị hộp thoại xác nhận khi nhấn nút Delete
            showDeleteStudentDialog(student)
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Tạo hành động khi nhấn nút "Add new"
        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun showAddStudentDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)

        val studentNameEditText = dialogView.findViewById<EditText>(R.id.edit_student_name)
        val studentIdEditText = dialogView.findViewById<EditText>(R.id.edit_student_id)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Student")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val studentName = studentNameEditText.text.toString()
                val studentId = studentIdEditText.text.toString()

                if (studentName.isNotBlank() && studentId.isNotBlank()) {
                    // Thêm sinh viên vào danh sách
                    val newStudent = StudentModel(studentName, studentId)
                    studentList.add(newStudent)
                    studentAdapter.notifyItemInserted(studentList.size - 1) // Cập nhật RecyclerView
                } else {
                    Toast.makeText(this, "Please enter both name and ID", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun showEditStudentDialog(student: StudentModel) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)

        val studentNameEditText = dialogView.findViewById<EditText>(R.id.edit_student_name)
        val studentIdEditText = dialogView.findViewById<EditText>(R.id.edit_student_id)

        // Điền thông tin sinh viên hiện tại vào các EditText
        studentNameEditText.setText(student.studentName)
        studentIdEditText.setText(student.studentId)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val updatedName = studentNameEditText.text.toString()
                val updatedId = studentIdEditText.text.toString()

                if (updatedName.isNotBlank() && updatedId.isNotBlank()) {
                    // Cập nhật thông tin sinh viên trong danh sách
                    val updatedStudent = StudentModel(updatedName, updatedId)
                    val index = studentList.indexOf(student)
                    if (index != -1) {
                        studentList[index] = updatedStudent
                        studentAdapter.notifyItemChanged(index) // Cập nhật RecyclerView
                    }
                } else {
                    Toast.makeText(this, "Please enter both name and ID", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun showDeleteStudentDialog(student: StudentModel) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete this student?")
            .setPositiveButton("Yes") { _, _ ->
                // Xóa sinh viên khỏi danh sách
                studentList.remove(student)
                studentAdapter.notifyDataSetChanged() // Cập nhật RecyclerView
                Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }
}

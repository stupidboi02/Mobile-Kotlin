package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listViewStudents: ListView
    private lateinit var studentAdapter: ArrayAdapter<String>
    private val students = mutableListOf<Student>()
    private val ADD_EDIT_REQUEST_CODE = 1
    private var editIndex: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listViewStudents = findViewById(R.id.listViewStudents)
        studentAdapter = StudentAdapter(this, students)
        listViewStudents.adapter = studentAdapter
        registerForContextMenu(listViewStudents)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_student -> {
                openAddEditActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menu_edit_student -> {
                editIndex = info.position
                val student = students[info.position]
                openAddEditActivity(student.id, student.name)
                true
            }
            R.id.menu_delete_student -> {
                students.removeAt(info.position)
                updateListView()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun openAddEditActivity(studentId: String? = null, studentName: String? = null) {
        val intent = Intent(this, AddEditStudentActivity::class.java)
        intent.putExtra("student_id", studentId)
        intent.putExtra("student_name", studentName)
        startActivityForResult(intent, ADD_EDIT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val id = data?.getStringExtra("student_id")
            val name = data?.getStringExtra("student_name")
            if (id != null && name != null) {
                if (editIndex == null) {
                    students.add(Student(id, name))
                } else {
                    students[editIndex!!].id = id
                    students[editIndex!!].name = name
                    editIndex = null
                }
                updateListView()
            }
        }
    }

    private fun updateListView() {
        studentAdapter.clear()
        studentAdapter.addAll(students.map { "${it.id} - ${it.name}" })
        studentAdapter.notifyDataSetChanged()
    }
}
package com.example.studentmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter (private val context: Context, private val students: List<Student>) : BaseAdapter() {
    override fun getCount(): Int = students.size


    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
        val student = students[position]

        val txtId = view.findViewById<TextView>(R.id.txtStudentId)
        val txtName = view.findViewById<TextView>(R.id.txtStudentName)

        txtId.text = student.id
        txtName.text = student.name

        return view
    }
}
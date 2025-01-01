package com.example.studentmanager_room.repository

import androidx.lifecycle.LiveData
import com.example.studentmanager_room.database.Student
import com.example.studentmanager_room.database.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }

    suspend fun update(student: Student) {
        studentDao.update(student)
    }

    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }
}
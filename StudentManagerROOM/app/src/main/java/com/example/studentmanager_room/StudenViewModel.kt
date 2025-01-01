package com.example.studentmanager_room.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.studentmanager_room.database.AppDatabase
import com.example.studentmanager_room.database.Student
import com.example.studentmanager_room.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val studentDao = AppDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}
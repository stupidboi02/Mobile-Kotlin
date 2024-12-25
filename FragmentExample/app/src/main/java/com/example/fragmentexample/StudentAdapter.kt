package com.example.fragmentexample
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class StudentAdapter(
    private val students: List<StudentModel>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // ViewHolder to hold view references for each item
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    }

    // Inflate item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_item, parent, false
        )
        return StudentViewHolder(itemView)
    }

    // Return the total number of items
    override fun getItemCount(): Int = students.size

    // Bind data to views for each item
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId

        // Set click listener for the item
//        holder.itemView.setOnClickListener {
//            onClick(student) // Trigger the callback with the clicked item
//        }
    }

}
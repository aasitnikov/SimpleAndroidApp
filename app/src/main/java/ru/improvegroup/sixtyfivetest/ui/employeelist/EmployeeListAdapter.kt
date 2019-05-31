package ru.improvegroup.sixtyfivetest.ui.employeelist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.common.inflate

class EmployeeListAdapter(
    private val onClick: (Employee) -> Unit
) : ListAdapter<Employee, EmployeeListAdapter.ViewHolder>(ItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO: maybe custom item layout
        return ViewHolder(parent.inflate(android.R.layout.simple_list_item_2), onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))


    class ViewHolder(view: View, private val onClick: (Employee) -> Unit) : RecyclerView.ViewHolder(view) {

        private val text1: TextView = view.findViewById(android.R.id.text1)
        private val text2: TextView = view.findViewById(android.R.id.text2)

        fun bind(employee: Employee) {
            itemView.setOnClickListener { onClick(employee) }
            text1.text = with(employee) { "$firstName $lastName" }
            //TODO: birthday
            text2.text = "26"
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean = oldItem == newItem
    }
}
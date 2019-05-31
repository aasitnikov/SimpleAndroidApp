package ru.improvegroup.sixtyfivetest.ui.specialty

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.ui.common.inflate

class SpecialtyAdapter : ListAdapter<Specialty, SpecialtyAdapter.ViewHolder>(ItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(android.R.layout.simple_list_item_1))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView = view.findViewById(android.R.id.text1)

        fun bind(specialty: Specialty) {
            textView.text = specialty.name
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<Specialty>() {
        override fun areItemsTheSame(oldItem: Specialty, newItem: Specialty): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Specialty, newItem: Specialty): Boolean = oldItem == newItem
    }
}

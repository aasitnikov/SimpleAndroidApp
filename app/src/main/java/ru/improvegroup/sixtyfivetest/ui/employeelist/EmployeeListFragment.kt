package ru.improvegroup.sixtyfivetest.ui.employeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_employee_list.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.android.MainActivity

class EmployeeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "EmployeeListFragment"
        setupListeners()
    }

    private fun setupListeners() {
        button_details.setOnClickListener { (activity as MainActivity).navigateToDetails() }
    }

    companion object {
        fun newInstance() = EmployeeListFragment()
    }
}
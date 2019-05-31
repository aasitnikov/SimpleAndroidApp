package ru.improvegroup.sixtyfivetest.ui.employeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_employee_list.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.android.MainActivity
import ru.improvegroup.sixtyfivetest.ui.common.injectViewModel
import ru.improvegroup.sixtyfivetest.ui.common.observe

class EmployeeListFragment : Fragment() {

    private val viewModel: IEmployeeListViewModel by lazy {
        injectViewModel(EmployeeListViewModel::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "EmployeeListFragment"
        setupListeners()
        bindViewModel()
    }

    private fun setupListeners() {
        button_details.setOnClickListener { (activity as MainActivity).navigateToDetails() }
    }

    private fun bindViewModel() {
        observe(viewModel.employeeList) { textView_content.text = it.toString() }
    }

    companion object {
        fun newInstance() = EmployeeListFragment()
    }
}
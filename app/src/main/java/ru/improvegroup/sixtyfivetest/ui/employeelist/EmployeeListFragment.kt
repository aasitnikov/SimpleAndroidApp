package ru.improvegroup.sixtyfivetest.ui.employeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_employee_list.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.common.injectViewModel
import ru.improvegroup.sixtyfivetest.ui.common.observe

class EmployeeListFragment : Fragment() {

    private val viewModel: IEmployeeListViewModel by lazy {
        injectViewModel(EmployeeListViewModel::class)
    }

    private val adapter = EmployeeListAdapter(::onEmployeeClicked)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.employee_list_toolbar_title)
        setupRecyclerView()
        bindViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView_employee.adapter = adapter
        recyclerView_employee.layoutManager = LinearLayoutManager(context!!)
    }

    private fun bindViewModel() {
        observe(viewModel.employeeList) { adapter.submitList(it) }
    }

    private fun onEmployeeClicked(employee: Employee) = viewModel.navigateToDetails(employee)

    companion object {
        fun newInstance() = EmployeeListFragment()
    }
}
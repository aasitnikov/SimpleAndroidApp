package ru.improvegroup.sixtyfivetest.ui.employeelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_employee_list.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.di.Scopes
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.common.injectViewModel
import ru.improvegroup.sixtyfivetest.ui.common.observe
import toothpick.Toothpick
import toothpick.config.Module

class EmployeeListFragment : Fragment() {

    private val argSpecialtyId: Int get() = arguments!!.getInt(ARG_SPECIALITY_ID)
    private val viewModel by lazy {
        Toothpick.openScopes(Scopes.APP, Scopes.EMPLOYEE_LIST).apply {
            installModules(Module().apply {
                bind(Int::class.javaObjectType).toInstance(argSpecialtyId)
            })
        }
        injectViewModel(EmployeeListViewModel::class, Scopes.EMPLOYEE_LIST)
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
        const val ARG_SPECIALITY_ID = "ARG_SPECIALITY_ID"

        fun newInstance(specialityId: Int) = EmployeeListFragment().apply {
            arguments = bundleOf(ARG_SPECIALITY_ID to specialityId)
        }
    }
}
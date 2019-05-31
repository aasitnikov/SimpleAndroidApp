package ru.improvegroup.sixtyfivetest.ui.employeedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_employee_details.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.android.MainActivity
import ru.improvegroup.sixtyfivetest.domain.entity.Employee

class EmployeeDetailsFragment : Fragment() {

    private val argEmployee: Employee by lazy {
        Gson().fromJson(arguments!!.getString(ARG_EMPLOYEE), Employee::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillEmployee()
    }

    private fun fillEmployee() {
        (activity as MainActivity).supportActionBar?.title = argEmployee.formatName()
//        textView_birthDay.text = TODO: birthday
//        textView_age.text = TODO: birthday
        textView_specialty.text = argEmployee.specialty.name
    }

    companion object {
        private const val ARG_EMPLOYEE = "ARG_EMPLOYEE"

        fun newInstance(employee: Employee) = EmployeeDetailsFragment().apply {
            arguments = bundleOf(ARG_EMPLOYEE to Gson().toJson(employee))
        }
    }
}
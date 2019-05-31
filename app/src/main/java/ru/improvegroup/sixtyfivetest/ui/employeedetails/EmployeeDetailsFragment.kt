package ru.improvegroup.sixtyfivetest.ui.employeedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_employee_details.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
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

        textView_birthDay.text = getString(R.string.employee_details_birthday, formatBirthDay(argEmployee.birthDay))
        val age: String = argEmployee.age()?.toString() ?: "-"
        textView_age.text = getString(R.string.employee_details_age, age)
        textView_specialty.text = getString(R.string.employee_details_speciality, argEmployee.specialty.name)
    }

    private fun formatBirthDay(birthDay: LocalDate?): String {
        return birthDay?.let { uiDateFormatter.format(it) } ?: "-"
    }

    companion object {
        private const val ARG_EMPLOYEE = "ARG_EMPLOYEE"

        private val uiDateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu")

        fun newInstance(employee: Employee) = EmployeeDetailsFragment().apply {
            arguments = bundleOf(ARG_EMPLOYEE to Gson().toJson(employee))
        }
    }
}
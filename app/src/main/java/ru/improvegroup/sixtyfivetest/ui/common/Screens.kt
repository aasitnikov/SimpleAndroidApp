package ru.improvegroup.sixtyfivetest.ui.common

import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.employeedetails.EmployeeDetailsFragment
import ru.improvegroup.sixtyfivetest.ui.employeelist.EmployeeListFragment
import ru.improvegroup.sixtyfivetest.ui.specialty.SpecialtyListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class EmployeeList(private val specialityId: Int) : SupportAppScreen() {
        override fun getFragment() = EmployeeListFragment.newInstance(specialityId)
    }

    class SpecialtyList : SupportAppScreen() {
        override fun getFragment() = SpecialtyListFragment.newInstance()
    }

    class EmployeeDetails(val employee: Employee) : SupportAppScreen() {
        override fun getFragment() = EmployeeDetailsFragment.newInstance(employee)
    }
}
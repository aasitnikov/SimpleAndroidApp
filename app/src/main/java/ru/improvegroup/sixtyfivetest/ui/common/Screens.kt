package ru.improvegroup.sixtyfivetest.ui.common

import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.employeedetails.EmployeeDetailsFragment
import ru.improvegroup.sixtyfivetest.ui.employeelist.EmployeeListFragment
import ru.improvegroup.sixtyfivetest.ui.specialty.SpecialtyListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class EmployeeList : SupportAppScreen() {
        override fun getFragment() = EmployeeListFragment.newInstance()
    }

    class SpecialtyList : SupportAppScreen() {
        override fun getFragment() = SpecialtyListFragment.newInstance()
    }

    class EmployeeDetails(val employee: Employee) : SupportAppScreen() {
        override fun getFragment() = EmployeeDetailsFragment.newInstance(employee)
    }
}
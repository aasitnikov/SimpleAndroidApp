package ru.improvegroup.sixtyfivetest.ui.employeelist

import androidx.lifecycle.LiveData
import ru.improvegroup.sixtyfivetest.domain.entity.Employee

interface IEmployeeListViewModel {
    val employeeList: LiveData<List<Employee>>

    fun navigateToDetails(employee: Employee)
}
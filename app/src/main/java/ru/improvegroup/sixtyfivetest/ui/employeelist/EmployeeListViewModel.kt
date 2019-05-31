package ru.improvegroup.sixtyfivetest.ui.employeelist

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.interactor.Interactor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(
    private val interactor: Interactor
) : BaseViewModel(), IEmployeeListViewModel {

    override val employeeList = MutableLiveData<List<Employee>>()

    init {
        interactor.getEmployees()
            .subscribe(employeeList::postValue, this::handleError)
            .untilDestroy()
    }
}
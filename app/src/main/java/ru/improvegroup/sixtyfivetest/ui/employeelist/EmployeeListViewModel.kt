package ru.improvegroup.sixtyfivetest.ui.employeelist

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(
    //TODO: must be local
    private val remote: RemoteGateway
) : BaseViewModel(), IEmployeeListViewModel {

    override val employeeList = MutableLiveData<List<Employee>>()

    init {
        remote.getAllEmployees()
            .subscribe(employeeList::postValue, this::handleError)
            .untilDestroy()
    }
}
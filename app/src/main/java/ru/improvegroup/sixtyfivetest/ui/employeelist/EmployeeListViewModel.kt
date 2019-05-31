package ru.improvegroup.sixtyfivetest.ui.employeelist

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.di.Scopes
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.interactor.Interactor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import ru.improvegroup.sixtyfivetest.ui.common.Screens
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(
    private val specialtyId: Int?,
    private val interactor: Interactor,
    private val router: Router
) : BaseViewModel() {

    val employeeList = MutableLiveData<List<Employee>>()

    init {
        interactor.getEmployees(specialtyId!!)
            .subscribe(employeeList::postValue, this::handleError)
            .untilDestroy()
    }

    fun navigateToDetails(employee: Employee) = router.navigateTo(Screens.EmployeeDetails(employee))

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(Scopes.EMPLOYEE_LIST)
    }
}
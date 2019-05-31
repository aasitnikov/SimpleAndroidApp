package ru.improvegroup.sixtyfivetest.ui.main

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.api.fake.FakeRemoteGateway
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.interactor.EmployeeInteractor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val interactor = EmployeeInteractor(FakeRemoteGateway)

    val employeeList = MutableLiveData<List<Employee>>()
    val loading = MutableLiveData(false)
    val errorVisible = MutableLiveData(false)

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        interactor.getEmployees()
            .bindProgress(loading)
            .subscribe(
                {
                    errorVisible.postValue(false)
                    employeeList.postValue(it)
                },
                {
                    errorVisible.postValue(true)
                    handleError(it)
                })
            .untilDestroy()
    }

    fun onRefresh() = loadEmployees()
}

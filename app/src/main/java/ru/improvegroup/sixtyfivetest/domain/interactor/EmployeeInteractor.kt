package ru.improvegroup.sixtyfivetest.domain.interactor

import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import javax.inject.Inject

class EmployeeInteractor @Inject constructor(
    private val remote: RemoteGateway
) {

    fun getEmployees(): Single<List<Employee>> = remote.getAllEmployees()
}
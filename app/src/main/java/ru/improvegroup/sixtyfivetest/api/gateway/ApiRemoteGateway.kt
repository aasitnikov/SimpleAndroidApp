package ru.improvegroup.sixtyfivetest.api.gateway

import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.api.model.EmployeeApiModel
import ru.improvegroup.sixtyfivetest.api.model.SpecialtyApiModel
import ru.improvegroup.sixtyfivetest.api.retrofit.ApiService
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import javax.inject.Inject

class ApiRemoteGateway @Inject constructor(
    private val apiService: ApiService
) : RemoteGateway {

    override fun getAllEmployees(): Single<List<Employee>> {
        return apiService.getAllEmployees()
            .map { it.response.map(::mapApiToDomain) }
    }

    private fun mapApiToDomain(source: EmployeeApiModel): Employee = with(source) {
        return Employee(
            0,
            firstName.toLowerCase().capitalize(),
            lastName.toLowerCase().capitalize(),
            //TODO: birthday
//            birthday,
            avatarUrl?.takeIf { it.isNotEmpty() },
            mapApiToDomain(specialty[0])
        )
    }

    private fun mapApiToDomain(source: SpecialtyApiModel): Specialty = with(source) {
        Specialty(
            id,
            name
        )
    }
}
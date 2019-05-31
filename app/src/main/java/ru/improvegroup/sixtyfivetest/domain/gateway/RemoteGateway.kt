package ru.improvegroup.sixtyfivetest.domain.gateway

import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.domain.entity.Employee

interface RemoteGateway {
    fun getAllEmployees() : Single<List<Employee>>
}
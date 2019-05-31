package ru.improvegroup.sixtyfivetest.domain.gateway

import io.reactivex.Completable
import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty

interface LocalGateway {

    fun getEmployees(specialtyId: Int): Single<List<Employee>>
    fun saveEmployees(list: List<Employee>): Completable

    fun getSpecialties(): Single<List<Specialty>>
    fun saveSpecialities(list: List<Specialty>): Completable
}
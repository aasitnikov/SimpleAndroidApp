package ru.improvegroup.sixtyfivetest.domain.interactor

import io.reactivex.Completable
import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.domain.gateway.LocalGateway
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import javax.inject.Inject

class Interactor @Inject constructor(
    private val remote: RemoteGateway,
    private val local: LocalGateway
) {

    fun fetchFromApi(): Completable {
        return remote.getAllEmployees()
            .flatMapCompletable { list ->
                Completable.mergeArray(
                    local.saveEmployees(list),
                    local.saveSpecialities(list.map { it.specialty }.distinctBy { it.id })
                )
            }
    }

    fun getEmployees(): Single<List<Employee>> = local.getEmployees()
    fun getSpecialties(): Single<List<Specialty>> = local.getSpecialties()
}
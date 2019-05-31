package ru.improvegroup.sixtyfivetest.db.gateway

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.improvegroup.sixtyfivetest.db.model.EmployeeDbModel
import ru.improvegroup.sixtyfivetest.db.model.SpecialtyDbModel
import ru.improvegroup.sixtyfivetest.db.room.AppDatabase
import ru.improvegroup.sixtyfivetest.db.room.EmployeeDao
import ru.improvegroup.sixtyfivetest.db.room.SpecialtyDao
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.domain.gateway.LocalGateway
import javax.inject.Inject

class DbLocalGateway @Inject constructor(
    database: AppDatabase
) : LocalGateway {

    private val employeeDao: EmployeeDao = database.employeeDao()
    private val specialtyDao: SpecialtyDao = database.specialtyDao()


    override fun getEmployees(): Single<List<Employee>> {
        return employeeDao.getAll().subscribeOn(Schedulers.io()).map { it.map(::mapDbToDomain) }
    }

    private fun mapDbToDomain(source: EmployeeDbModel): Employee {
        return Employee(
            source.uid,
            source.firstName,
            source.lastName,
            source.avatar,
            Specialty(0, "") //TODO
        )
    }

    override fun saveEmployees(list: List<Employee>): Completable {
        return employeeDao.deleteAll().subscribeOn(Schedulers.io()).andThen(
            employeeDao.insertAll(list.map(::mapDomainToDb))
        )
    }

    private fun mapDomainToDb(source: Employee) = EmployeeDbModel(
        source.id,
        source.firstName,
        source.lastName,
        source.photoUrl
        //Specialty(0, "") //TODO
    )

    override fun getSpecialties(): Single<List<Specialty>> {
        return specialtyDao.getAll().subscribeOn(Schedulers.io()).map { it.map(::mapDbToDomain) }
    }

    private fun mapDbToDomain(source: SpecialtyDbModel) = Specialty(
        source.uid,
        source.name
    )

    override fun saveSpecialities(list: List<Specialty>): Completable {
        return specialtyDao.deleteAll().subscribeOn(Schedulers.io()).andThen(
            specialtyDao.insertAll(list.map(this::mapDomainToDb))
        )
    }

    private fun mapDomainToDb(source: Specialty) = SpecialtyDbModel(
        source.id,
        source.name
    )
}
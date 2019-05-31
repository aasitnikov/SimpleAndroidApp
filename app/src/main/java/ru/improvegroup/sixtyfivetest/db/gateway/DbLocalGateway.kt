package ru.improvegroup.sixtyfivetest.db.gateway

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
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

    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    override fun getEmployees(specialtyId: Int): Single<List<Employee>> {
        return specialtyDao.getAll().subscribeOn(Schedulers.io()).flatMap { specList ->
            val map = specList.associateBy { it.uid }
            employeeDao.getAll()
                .map { list ->
                    list
                        .filter { it.specialtyId == specialtyId }
                        .map { mapDbToDomain(it, map) }
                }
        }
    }

    private fun mapDbToDomain(source: EmployeeDbModel, map: Map<Int, SpecialtyDbModel>): Employee {
        val specialty = map.getValue(source.specialtyId)
        return Employee(
            source.uid,
            source.firstName,
            source.lastName,
            source.avatar,
            source.birthDay?.let { LocalDate.from(dateFormatter.parse(it)) },
            Specialty(specialty.uid, specialty.name)
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
        source.photoUrl,
        source.birthDay?.let { dateFormatter.format(it) },
        source.specialty.id
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
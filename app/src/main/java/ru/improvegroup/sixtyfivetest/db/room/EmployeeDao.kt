package ru.improvegroup.sixtyfivetest.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.db.model.EmployeeDbModel

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getAll(): Single<List<EmployeeDbModel>>

    @Query("SELECT * FROM employee WHERE uid = :id")
    fun loadById(id: Int): Single<List<EmployeeDbModel>>

    @Insert
    fun insertAll(list: List<EmployeeDbModel>): Completable

    @Query("DELETE from employee")
    fun deleteAll(): Completable
}
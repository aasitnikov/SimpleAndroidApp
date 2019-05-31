package ru.improvegroup.sixtyfivetest.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.db.model.SpecialtyDbModel

@Dao
interface SpecialtyDao {

    @Query("SELECT * FROM specialty")
    fun getAll(): Single<List<SpecialtyDbModel>>

    @Query("SELECT * FROM specialty WHERE uid = :id")
    fun loadById(id: Int): Single<List<SpecialtyDbModel>>

    @Insert
    fun insertAll(list: List<SpecialtyDbModel>): Completable

    @Query("DELETE FROM specialty")
    fun deleteAll(): Completable
}
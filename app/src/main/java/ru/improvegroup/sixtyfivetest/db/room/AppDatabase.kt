package ru.improvegroup.sixtyfivetest.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.improvegroup.sixtyfivetest.db.model.EmployeeDbModel
import ru.improvegroup.sixtyfivetest.db.model.SpecialtyDbModel

@Database(entities = [EmployeeDbModel::class, SpecialtyDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao
    abstract fun specialtyDao(): SpecialtyDao
}
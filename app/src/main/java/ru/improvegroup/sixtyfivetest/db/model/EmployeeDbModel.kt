package ru.improvegroup.sixtyfivetest.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class EmployeeDbModel constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "avatar")
    val avatar: String?,
    @ColumnInfo(name = "birth_day")
    val birthDay: String?,
    @ColumnInfo(name = "spec_id")
    val specialtyId: Int
)
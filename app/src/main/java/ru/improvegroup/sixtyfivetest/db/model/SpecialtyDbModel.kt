package ru.improvegroup.sixtyfivetest.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialty")
data class SpecialtyDbModel(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String
)
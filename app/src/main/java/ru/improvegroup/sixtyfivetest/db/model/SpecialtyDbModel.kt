package ru.improvegroup.sixtyfivetest.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialty")
data class SpecialtyDbModel(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String
)
package ru.improvegroup.sixtyfivetest.domain.entity

import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.ChronoUnit

data class Employee constructor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photoUrl: String?,
    val birthDay: LocalDate?,
    val specialty: Specialty
) {

    fun formatName() = "$lastName $firstName"

    fun age(): Int? {
        return ChronoUnit.YEARS.between(birthDay ?: return null, LocalDate.now()).toInt()
    }
}
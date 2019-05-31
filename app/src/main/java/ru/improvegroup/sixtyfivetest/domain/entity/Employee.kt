package ru.improvegroup.sixtyfivetest.domain.entity

data class Employee(
    val id: Int,
    val firstName: String,
    val lastName: String,
    //TODO: birthday
    val photoUrl: String,
    val specialty: Specialty
)
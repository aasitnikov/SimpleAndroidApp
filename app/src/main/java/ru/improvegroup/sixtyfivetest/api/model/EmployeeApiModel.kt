package ru.improvegroup.sixtyfivetest.api.model

import com.google.gson.annotations.SerializedName

data class EmployeeApiModel(
    @SerializedName("f_name")
    val firstName: String,
    @SerializedName("l_name")
    val lastName: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("avatr_url")
    val avatarUrl: String?,
    @SerializedName("specialty")
    val specialty: List<SpecialtyApiModel>
)
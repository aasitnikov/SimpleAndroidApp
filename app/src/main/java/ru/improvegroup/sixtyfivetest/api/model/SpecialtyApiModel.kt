package ru.improvegroup.sixtyfivetest.api.model

import com.google.gson.annotations.SerializedName

data class SpecialtyApiModel(
    @SerializedName("specialty_id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
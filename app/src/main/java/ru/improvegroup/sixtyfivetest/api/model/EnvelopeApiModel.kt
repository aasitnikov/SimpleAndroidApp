package ru.improvegroup.sixtyfivetest.api.model

import com.google.gson.annotations.SerializedName

class EnvelopeApiModel<T>(
    @SerializedName("response")
    val response: T
)
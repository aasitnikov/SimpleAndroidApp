package ru.improvegroup.sixtyfivetest.api.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import ru.improvegroup.sixtyfivetest.api.model.EmployeeApiModel
import ru.improvegroup.sixtyfivetest.api.model.EnvelopeApiModel

interface ApiService {

    @GET("65gb/static/raw/master/testTask.json")
    fun getAllEmployees(): Single<EnvelopeApiModel<List<EmployeeApiModel>>>

    companion object {
        const val BASE_URL = "https://gitlab.65apps.com"
    }
}
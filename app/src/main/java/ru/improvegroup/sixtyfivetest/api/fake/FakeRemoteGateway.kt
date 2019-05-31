package ru.improvegroup.sixtyfivetest.api.fake

import io.reactivex.Single
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import java.util.concurrent.TimeUnit

object FakeRemoteGateway : RemoteGateway {

    override fun getAllEmployees(): Single<List<Employee>> {
        return Single
            .defer {
                if (Math.random() > 0.5) {
                    Single.error<List<Employee>>(Exception("Unexpected error"))
                } else {
                    Single.just(
                        listOf(
                            Employee(
                                1,
                                "Name",
                                "Surname",
                                "http://img2.russia.ru/upimg/author/422/image.jpg",
                                Specialty(
                                    1,
                                    "Manager"
                                )
                            )
                        ))
                }
            }
            .delaySubscription(1, TimeUnit.SECONDS)
    }
}
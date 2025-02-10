package com.example

import com.example.routing.bookRoutes
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureSerialization() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        bookRoutes()
    }
}

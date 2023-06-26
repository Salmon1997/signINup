package com.signup

import com.signup.login.configureLoginRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.signup.plugins.*
import com.signup.register.configureRegisterRouting
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:postgresql://localhost5432/base", driver = "org.postgres.Driver" , password = "admin123")


    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSerialization()
    }
        .start(wait = true)
}


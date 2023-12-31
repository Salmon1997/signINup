package com.signup.register


import com.signup.login.LoginResponseRemote
import java.util.UUID
import com.signup.cache.InMemoryCache
import com.signup.cache.InMemoryCache.token
import com.signup.cache.TokenCache
import com.signup.until.isValidEmail
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable



fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
//            val receive = call.receive<RegisterReceiveRemote>()
//            if(!receive.email.isValidEmail()){
//                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
//            }

//            if (InMemoryCache.userList.map { it.login }.contains(receive.login)){
//                call.respond(HttpStatusCode.Conflict,"User already exist")
//            }
//
//            val token = UUID.randomUUID().toString()
//            InMemoryCache.userList.add(receive)
//            InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
//
//
//            call.respond(RegisterResponseRemote(token = token))

        }
    }
}

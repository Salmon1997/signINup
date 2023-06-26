package com.signup.login

import com.signup.cache.InMemoryCache
import com.signup.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import java.util.UUID


fun Application.configureLoginRouting() {
    routing {
        post("/login") {
              val loginController = LoginController(call)
            loginController.performLogin()
//            val receive = call.receive<LoginReceiveRemote>()
//            val first = InMemoryCache.userList.firstOrNull{it.login==receive.login}
//
//            if(first == null){
//                call.respond(HttpStatusCode.BadRequest, "user not found")
//            }else if(first.password == receive.password ){
//                val token = UUID.randomUUID().toString()
//                InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
//                call.respond(LoginResponseRemote(token = token))
//            }else{
//                call.respond(HttpStatusCode.BadRequest,"not found")
//            }


        }
    }
}

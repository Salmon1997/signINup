package com.signup.login


import com.signup.database.Users
import com.signup.database.tokens.TokenDTO
import com.signup.database.tokens.Tokens
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call:ApplicationCall){
   suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)

        if(userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "user not found")
        }else if(userDTO.password == receive.password ){
            val token = UUID.randomUUID().toString()
            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(), login = receive.login,
                    token = token
                )
            )
            call.respond(LoginResponseRemote(token = token))
        }else{
            call.respond(HttpStatusCode.BadRequest,"not found")
        }
    }
}
package com.signup.register

import com.signup.cache.InMemoryCache
import com.signup.cache.TokenCache
import com.signup.database.UserDTO
import com.signup.database.Users
import com.signup.database.tokens.TokenDTO
import com.signup.database.tokens.Tokens
import com.signup.until.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(private val call:ApplicationCall){

    suspend fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
            if(!registerReceiveRemote.email.isValidEmail()){
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }
        val userDTO = Users.fetchUser(registerReceiveRemote.login)


        if(userDTO != null){
            call.respond(HttpStatusCode.Conflict,"User already exist")
        }else{
            val token = UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    login = registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    email = registerReceiveRemote.email,
                    username = ""
                )
            )
            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(), login = registerReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))

        }

    }
}
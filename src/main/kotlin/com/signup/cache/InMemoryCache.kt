package com.signup.cache

import com.signup.register.RegisterReceiveRemote

data class TokenCache(
    val login:String,
    val token:String
)


object InMemoryCache {
val userList:MutableList<RegisterReceiveRemote> = mutableListOf()
val token:MutableList<TokenCache> = mutableListOf()
}
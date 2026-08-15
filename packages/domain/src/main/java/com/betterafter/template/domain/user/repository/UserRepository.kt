package com.betterafter.template.domain.user.repository

import com.betterafter.template.domain.user.entity.User

interface UserRepository {
    suspend fun getUser(id: String): User
}

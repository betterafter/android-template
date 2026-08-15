package com.betterafter.template.data.user.repository

import com.betterafter.template.data.user.api.UserApi
import com.betterafter.template.data.user.mapper.toDomain
import com.betterafter.template.domain.user.entity.User
import com.betterafter.template.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getUser(id: String): User {
        // api가 없어서 이렇게 내려온다는 가정 하에..
        // val userDto = userApi.getUser(id)
        // return userDto.toDomain()
        
        return User(id = id, name = "Sample User", email = "sample@example.com")
    }
}

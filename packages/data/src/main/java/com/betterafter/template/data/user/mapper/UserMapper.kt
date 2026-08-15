package com.betterafter.template.data.user.mapper

import com.betterafter.template.data.user.api.UserDto
import com.betterafter.template.domain.user.entity.User

fun UserDto.toDomain(): User = User(
    id = id,
    name = name,
    email = email
)

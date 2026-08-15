package com.betterafter.template.domain.user.usecase

import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.user.entity.User
import com.betterafter.template.domain.user.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String): DataState<User> = DataState.guard {
        repository.getUser(id)
    }
}

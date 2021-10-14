package com.cmd.cmd_app_android.domain.usecase

import com.solid.cmd_app_android.data.models.UserDTO
import com.cmd.cmd_app_android.data.repository.UserRepository

class UpdateUser constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: UserDTO) = repository.updateUser(user)
}
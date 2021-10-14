package com.solid.cmd_app_android.data.repository

import android.util.Log
import com.cmd.cmd_app_android.data.repository.UserRepository
import com.solid.cmd_app_android.common.Resource
import com.solid.cmd_app_android.data.api.UserApi
import com.solid.cmd_app_android.data.models.UserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl constructor(
    private val api: UserApi
): UserRepository {

    override suspend fun createUser(user: UserDTO): Flow<Resource<UserDTO>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.createUser(user)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success<UserDTO>(it))
                }
            }
        }catch (e: Exception) {
            emit(Resource.Error<UserDTO>(e.message))
        }
    }

    override suspend fun getUsers(): Flow<Resource<List<UserDTO>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getUsers()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success<List<UserDTO>>(it))
                }
            }
        }catch (e: Exception) {
            emit(Resource.Error<List<UserDTO>>(e.message))
        }
    }

    override suspend fun getUserById(id: String): Flow<Resource<UserDTO>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getUserById(id)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success<UserDTO>(it))
                }
            }
        }catch (e: Exception) {
            emit(Resource.Error<UserDTO>(e.message))
        }
    }

    override suspend fun deleteUser(id: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.deleteUser(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success<String>(it))
                }
            }
        }catch (e: Exception) {
            emit(Resource.Error<String>(e.message))
        }
    }

    override suspend fun updateUser(user: UserDTO): Flow<Resource<UserDTO>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.updateUser(user, user.id.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success<UserDTO>(it))
                }
            }
            emit(Resource.Success<UserDTO>())
        }catch (e: Exception) {
            emit(Resource.Error<UserDTO>(e.message))
        }
    }

    override suspend fun getUserByEmail(email: String): Flow<Resource<UserDTO>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getUserByEmail(email)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success<UserDTO>(it))
                }
            }
            emit(Resource.Success<UserDTO>())
        }catch (e: Exception) {
            emit(Resource.Error<UserDTO>(e.message))
            Log.d("TAG", "getUserByEmail: ${e.message}")
        }
    }
}
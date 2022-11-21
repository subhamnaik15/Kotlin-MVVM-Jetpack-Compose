package com.campose.jetmvvm.repository

import com.campose.jetmvvm.api.UserServiceAPI
import com.campose.jetmvvm.model.request.UserRequest
import com.campose.jetmvvm.model.response.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserServiceAPI) {

    suspend fun signInUser(user : UserRequest?=null): List<UserResponse>{
        //ignore userRequest as there is no params to pass in the API
        return  userAPI.signIn()
    }
}
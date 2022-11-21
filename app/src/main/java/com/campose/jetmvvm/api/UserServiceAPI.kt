package com.campose.jetmvvm.api

import com.campose.jetmvvm.model.response.UserResponse
import retrofit2.http.GET

interface UserServiceAPI {
    @GET("/users")
    suspend fun signIn(): List<UserResponse>
}
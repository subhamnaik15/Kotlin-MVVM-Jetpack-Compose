package com.campose.jetmvvm.model.data

import com.campose.jetmvvm.model.response.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun fetchUser(): List<User>
}
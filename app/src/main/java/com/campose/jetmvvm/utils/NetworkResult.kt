package com.campose.jetmvvm.utils

import com.campose.jetmvvm.model.response.UserResponse

sealed class NetworkState<T>(val data: T? = null,val message: String? = null) {

    class Loading<T> : NetworkState<T>()
    class Default<T> : NetworkState<T>()
    class Success<T>(data: T? = null ) : NetworkState<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkState<T>(data, message)

}

sealed class UserState {
    object DefaultState : UserState()
    object LoadingState : UserState()
    object LogOutSuccess : UserState()
    data class LogInSuccess(val users: List<UserResponse>) : UserState()
    data class Error(val errorMessage: String) : UserState()
}
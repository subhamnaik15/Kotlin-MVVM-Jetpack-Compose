package com.campose.jetmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campose.jetmvvm.model.data.UserService
import com.campose.jetmvvm.model.response.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    val userState = MutableStateFlow<UserState>(UserState.DefaultState)

    suspend fun signIn(email: String, password: String) {
        viewModelScope.launch {
            userState.tryEmit(UserState.LoadingState)
            withContext(Dispatchers.IO) {
                try {
                    delay(2000)
                    val user = userService.fetchUser()
                    userState.tryEmit(UserState.LogInSuccess(user))
                } catch (e: Exception) {
                    userState.tryEmit(UserState.Error(e.localizedMessage))
                }
            }
        }
    }


    
    suspend fun signOut() {
        viewModelScope.launch {
            userState.tryEmit(UserState.LoadingState)
            withContext(Dispatchers.IO) {
                try {
                    delay(2000)
                    userState.tryEmit(UserState.LogOutSuccess)
                } catch (e: Exception) {
                    userState.tryEmit(UserState.Error(e.localizedMessage))
                }
            }
        }
    }
}
sealed class UserState {
    object DefaultState : UserState()
    object StartState : UserState()
    object LoadingState : UserState()
    object LogOutSuccess : UserState()
    data class LogInSuccess(val users: List<User>) : UserState()
    data class Error(val errorMessage: String) : UserState()
}
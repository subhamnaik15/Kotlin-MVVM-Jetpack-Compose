package com.campose.jetmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campose.jetmvvm.repository.UserRepository
import com.campose.jetmvvm.utils.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userState = MutableStateFlow<UserState>(UserState.DefaultState)

    suspend fun signIn(email: String, password: String) {
        viewModelScope.launch {
            userState.tryEmit(UserState.LoadingState)
            withContext(Dispatchers.IO) {
                try {
                    delay(2000)
                    val user = userRepository.signInUser()
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

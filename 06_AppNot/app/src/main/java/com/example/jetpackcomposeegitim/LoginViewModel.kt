package com.example.jetpackcomposeegitim


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.jetpackcomposeegitim.User


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, password)
            if (user != null) {
                _loginStatus.value = true
                _errorMessage.value = null
            } else {
                _loginStatus.value = false
                _errorMessage.value = "Kullanıcı adı veya şifre yanlış"
            }
        }
    }

    private val _registrationStatus = MutableLiveData<Boolean>()
    val registrationStatus: LiveData<Boolean> = _registrationStatus

    suspend fun register(username: String, password: String): Boolean {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val user = User(username, password)
            userRepository.insertUser(user)
            _registrationStatus.postValue(true)
            return true
        } else {
            _registrationStatus.postValue(false)
            return false
        }
    }
}

package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()


    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val db by lazy { UserDatabase(getApplication()).userDao() }


    fun login(username: String, password: String) {
        coroutineScope.launch {

            val userDetail = db.getUser(username)



            if (userDetail == null) {
                withContext(Dispatchers.Main) {
                    error.value = "Invalid user"
                }
            } else {
                if (userDetail.username == username && userDetail.passwordHash == password) {
                    LoginState.login(userDetail)
                    withContext(Dispatchers.Main) {
                        loginComplete.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        error.value = "password is incorrect"
                    }
                }
            }

        }


    }
}

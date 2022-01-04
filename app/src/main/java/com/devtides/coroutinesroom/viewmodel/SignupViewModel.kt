package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.User
import com.devtides.coroutinesroom.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    val coroutineScope=CoroutineScope(Dispatchers.IO)
    val db by lazy { UserDatabase(getApplication()).userDao() }

/*in this function ,coroutine is used to insert data into Room database*/
    fun signup(username: String, password: String, info: String) {

        coroutineScope.launch {
            val user=db.getUser(username)

            if(user!=null){
                //if user already exists, need to show error message .so need to change to main dispatcher.
              withContext(Dispatchers.Main){
                  error.value="User already exists"
              }
            }
            else{
                // create new user by calling insert()

                val userToInsert=User(username,password.hashCode().toString(),info)
                val userId=db.insertUser(userToInsert)
                userToInsert.id=userId

                //update login state using this user data
                LoginState.login(userToInsert)

                //after insert ,we need to navigate to Main page ,there we should see user name
                //for that we need to user main dispatcher and set value for "signupComplete"

                withContext(Dispatchers.Main){
                    signupComplete.value=true
                }

            }
        }
    }

}
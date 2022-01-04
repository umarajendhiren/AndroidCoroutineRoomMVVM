package com.devtides.coroutinesroom.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.R
import com.devtides.coroutinesroom.viewmodel.SignupViewModel
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupBtn.setOnClickListener { onSignup(it) }
        gotoLoginBtn.setOnClickListener { onGotoLogin(it) }

        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupComplete.observe(this, Observer { isComplete ->
            Toast.makeText(activity,"SignUp complete",Toast.LENGTH_SHORT).show()

            val action=SignupFragmentDirections.actionGoToMain()
            Navigation.findNavController(signupUsername).navigate(action)


        })

        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(activity,"error:$error",Toast.LENGTH_SHORT).show()
        })
    }

    private fun onSignup(v: View){
       /* val action = SignupFragmentDirections.actionGoToMain()
        Navigation.findNavController(v).navigate(action)*/

        val userName=signupUsername.text.toString()
        val password=signupPassword.text.toString()
        val info=otherInfo.text.toString()




        if(userName.isNullOrEmpty() || password.isNullOrEmpty() || info.isNullOrEmpty()){
            Toast.makeText(activity,"Please fill all feilds",Toast.LENGTH_LONG).show()
        }
        else{


            viewModel.signup(userName,password,info)
        }
    }

    private fun onGotoLogin(v: View) {
        val action = SignupFragmentDirections.actionGoToLogin()
        Navigation.findNavController(v).navigate(action)
    }
}

package com.chupryna.socialapplication.ui.authorization.signup_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment(), ISignUpView {

    private val presenter by lazy { SignUpPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
    }

    private fun initListeners() {
        signUpBtn.setOnClickListener { presenter.onSignUp(
            fullNameSignUpEt.text.toString(),
            emailSignUpEt.text.toString(),
            passwordSignUpEt.text.toString(),
            confirmPasswordSignUpEt.text.toString()
        ) }
    }

    override fun showFullNameError(msg: String) {
        fullNameSignUpEt.error = msg
        fullNameSignUpEt.requestFocus()
    }

    override fun showEmailError(msg: String) {
        emailSignUpEt.error = msg
        emailSignUpEt.requestFocus()
    }

    override fun showPasswordError(msg: String) {
        passwordSignUpEt.error = msg
        passwordSignUpEt.requestFocus()
    }

    override fun hideFullNameError() {
        fullNameSignUpEt.error = null
    }

    override fun hideEmailError() {
        emailSignUpEt.error = null
    }

    override fun hidePasswordError() {
        passwordSignUpEt.error = null
    }

    override fun checkNetwork(): Boolean {
        return (activity as AuthorizationActivity).presenter.isNetworkAvailable(context!!)
    }

    override fun showSignUpFailed(msg: String) {
        (activity as AuthorizationActivity).showToast(msg)
    }

    override fun hideKeyboard() {
        (activity as AuthorizationActivity).hideKeyboard(this.view!!)
    }

    override fun showSignIn() {
        (activity as AuthorizationActivity).replaceFragment(SignInFragment())
    }

}
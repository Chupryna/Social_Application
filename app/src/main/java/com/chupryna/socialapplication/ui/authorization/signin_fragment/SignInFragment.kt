package com.chupryna.socialapplication.ui.authorization.signin_fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : Fragment(), ISignInView {

    private val presenter by lazy { SingInPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
    }

    private fun initListeners() {
        showPasswordCb.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.onChangeCheckedShowPassword(isChecked)
        }

        signInBtn.setOnClickListener { presenter.onSignIn(emailEt.text.toString(), passwordEt.text.toString()) }
    }

    override fun showPassword() {
        passwordEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        passwordEt.setSelection(passwordEt.length())
    }

    override fun hidePassword() {
        passwordEt.transformationMethod = PasswordTransformationMethod.getInstance()
        passwordEt.setSelection(passwordEt.length())
    }

    override fun showEmailError(msg: String) {
        emailEt.error = msg
        emailEt.requestFocus()
    }

    override fun showPasswordError(msg: String) {
        passwordEt.error = msg
        passwordEt.requestFocus()
    }

    override fun hideEmailError() {
        emailEt.error = null
    }

    override fun hidePasswordError() {
        passwordEt.error = null
    }

    override fun showProgress() {
        (activity as AuthorizationActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as AuthorizationActivity).hideProgress()
    }

    override fun showAuthFailed(msg: String) {
        (activity as AuthorizationActivity).showToast(msg)
    }

    override fun hideKeyboard() {
        (activity as AuthorizationActivity).hideKeyboard(this.view!!)
    }
}
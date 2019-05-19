package com.chupryna.socialapplication.ui.authorization.signin_fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.firebase.MethodAuthorization
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import com.chupryna.socialapplication.ui.authorization.reset_password_fragment.ResetPasswordFragment
import com.chupryna.socialapplication.ui.authorization.signup_fragment.SignUpFragment
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : Fragment(), ISignInView {

    companion object {
        private const val RC_SIGN_IN = 1
    }

    private val presenter by lazy { SingInPresenter(this) }
    private val callbackManager by lazy { CallbackManager.Factory.create() }

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
        goToSignUpTv.setOnClickListener { presenter.onSignUp() }
        signInWithGoogleBtn.setOnClickListener { onSignInWithGoogle()}
        signInWithFacebookBtn.setOnClickListener { presenter.onSignInWithFacebook(signInWithFacebookBtn, callbackManager) }
        resetPasswordTv.setOnClickListener { presenter.onResetPassword() }
    }

    private fun onSignInWithGoogle() {
        val signInIntent = MethodAuthorization().signInWithGoogle(getFragmentContext())
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                presenter.firebaseSignInWithGoogle(account!!)
            } catch (e: ApiException) {
                showAuthFailed("Помилка авторизації")
            }
        }
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

    override fun getFragmentContext(): Context {
        return context!!
    }

    override fun showSignUp() {
        (activity as AuthorizationActivity).replaceFragment(SignUpFragment())
    }

    override fun showResetPassword() {
        (activity as AuthorizationActivity).replaceFragment(ResetPasswordFragment())
    }

    override fun showSnackBarSendEmail(msg: String, action: String, user: FirebaseUser) {
        Snackbar
            .make(this.view!!, msg, Snackbar.LENGTH_LONG)
            .setAction(action) { presenter.sendEmailVerification(user) }
            .show()
    }

    override fun startMainActivity(user: FirebaseUser) {
        (activity as AuthorizationActivity).startMainActivity(user)
    }
}
package com.chupryna.socialapplication.ui.authorization.reset_password_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment
import kotlinx.android.synthetic.main.fragment_reset_password.*

class ResetPasswordFragment : Fragment(), IResetPasswordView {

    private val presenter by lazy { ResetPasswordPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
    }

    private fun initListeners() {
        resetPasswordSendEmailBtn.setOnClickListener { presenter.onResetPassword(resetPasswordEmailEt.text.toString()) }
    }

    override fun showEmailError(msg: String) {
        resetPasswordEmailEt.error = msg
    }

    override fun hideEmailError() {
        resetPasswordEmailEt.error = null
    }

    override fun hideKeyboard() {
        (activity as AuthorizationActivity).hideKeyboard(this.view!!)
    }

    override fun showToast(msg: String) {
        (activity as AuthorizationActivity).showToast(msg)
    }

    override fun showProgress() {
        (activity as AuthorizationActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as AuthorizationActivity).hideProgress()
    }

    override fun goToSignIn() {
        (activity as AuthorizationActivity).replaceFragment(SignInFragment())
    }
}
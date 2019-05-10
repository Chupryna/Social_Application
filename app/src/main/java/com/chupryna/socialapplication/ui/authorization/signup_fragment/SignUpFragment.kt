package com.chupryna.socialapplication.ui.authorization.signup_fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment(), ISignUpView {

    companion object {
        private const val REQUEST_CODE_CHOOSE_FILE = 1
        private const val REQUEST_CODE_PERMISSIONS = 2
    }

    private val presenter by lazy { SignUpPresenter(this, context!!) }
    private var photo: Uri? = null

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
            confirmPasswordSignUpEt.text.toString(),
            photo) }

        photoSignUpIv.setOnClickListener { presenter.onPhotoChange() }
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

    override fun showSignUpFailed(msg: String) {
        (activity as AuthorizationActivity).showToast(msg)
    }

    override fun hideKeyboard() {
        (activity as AuthorizationActivity).hideKeyboard(this.view!!)
    }

    override fun showSignIn() {
        (activity as AuthorizationActivity).replaceFragment(SignInFragment())
    }

    override fun showProgress() {
        (activity as AuthorizationActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as AuthorizationActivity).hideProgress()
    }

    override fun checkPermission() {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            choosePhotoInStorage()
        else
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS && grantResults.size == 1)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                choosePhotoInStorage()
    }

    private fun choosePhotoInStorage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CHOOSE_FILE && resultCode == RESULT_OK) {
            val selectedImage = data?.data
            if (selectedImage != null) {
                photoSignUpIv.setImageURI(selectedImage)
                photo = selectedImage
            }
        }
    }
}
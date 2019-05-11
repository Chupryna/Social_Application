package com.chupryna.socialapplication.ui.authorization.signup_fragment

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.chupryna.socialapplication.data.model.ImageUploadResponse
import com.chupryna.socialapplication.data.model.User
import com.chupryna.socialapplication.data.photo.IPhotoDataSource
import com.chupryna.socialapplication.data.photo.PhotoRepository
import com.chupryna.socialapplication.utils.CompressImage
import com.chupryna.socialapplication.utils.ImageFilePath
import com.chupryna.socialapplication.utils.isNetworkAvailable
import com.google.firebase.auth.FirebaseUser
import java.io.File
import java.util.regex.Pattern

class SignUpPresenter(
    private val view: ISignUpView,
    private val context: Context) {

    companion object {
        private const val PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}"
    }

    private val model by lazy { FirebaseAuthorization() }

    fun onSignUp(fullName: String, email: String, password: String, confirmPassword: String, photo: Uri?) {
        if (!context.isNetworkAvailable()) {
            view.showToast("Відсутнє з'єднання з мережею")
            return
        }

        view.hideKeyboard()
        view.showProgress()
        if (isDataValid(fullName, email, password, confirmPassword)) {
            val photoPath = getCompressedPhotoPath(photo)
            if (photoPath != null) {
                val repository = PhotoRepository(context)
                repository.uploadImage(photoPath, object: IPhotoDataSource.IPhotoUploadCallback {
                    override fun onSuccess(response: ImageUploadResponse) {
                        createAccount(fullName, email, password, response.path)
                    }

                    override fun onFailure() {
                        createAccount(fullName, email, password,null)
                    }
                })
            } else
                createAccount(fullName, email, password, null)
        } else
            view.hideProgress()
    }

    private fun createAccount(fullName: String, email: String, password: String, photoPath: String?) {
        model.createNewAccount(User(fullName, email, password, photoPath), object : IFirebaseAuth.FirebaseUserCallback {
            override fun onSuccess(user: FirebaseUser) {
                view.hideProgress()
                onSignIn()
            }

            override fun onFailure(msg: String) {
                view.hideProgress()
                view.showToast(msg)
                view.showEmailError(msg)
            }
        })
    }

    private fun getCompressedPhotoPath(photo: Uri?): String? {
        return if (photo == null) null
        else {
            val imagePath = ImageFilePath().getPath(context, photo)
            val compressedImage = CompressImage(context).compressBitmap(File(imagePath), 2, 50)
            compressedImage.absolutePath
        }
    }

    fun onSignIn() {
        view.showToast("Користувач зареєстрований. Підтвердіть e-mail та увійдіть")
        view.showSignIn()
    }

    fun onPhotoChange() {
        view.checkPermission()
    }

    private fun isDataValid(fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        view.hideFullNameError()
        view.hideEmailError()
        view.hidePasswordError()

        if (TextUtils.isEmpty(fullName)) {
            view.showFullNameError("Заповніть поле")
            return false
        }

        if (TextUtils.isEmpty(email)) {
            view.showEmailError("Заповніть поле")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Некоректний формат e-mail")
            return false
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError("Заповніть поле")
            return false
        }

        if(!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            view.showPasswordError("Пароль занадто слабкий")
            return false
        }

        if (password != confirmPassword) {
            view.showPasswordError("Паролі не співпадають")
            return false
        }

        return true
    }
}
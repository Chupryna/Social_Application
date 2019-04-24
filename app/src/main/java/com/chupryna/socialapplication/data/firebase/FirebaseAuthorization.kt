package com.chupryna.socialapplication.data.firebase

import com.chupryna.socialapplication.data.model.User
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*

class FirebaseAuthorization : IFirebaseAuth {

    override fun attemptSignIn(email: String, password: String, callback: IFirebaseAuth.FirebaseUserCallback) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                callback.onSuccess(authResult.user)
            }
            .addOnFailureListener { exception ->
                when (exception) {
                    is FirebaseAuthInvalidUserException -> callback.onFailure("Невірний email або пароль")
                    is FirebaseAuthInvalidCredentialsException -> callback.onFailure("Невірний email або пароль")
                    else -> callback.onFailure("Помилка аутентифікації")
                }
            }
    }

    override fun attemptSignInWithGoogle(account: GoogleSignInAccount, callback: IFirebaseAuth.FirebaseUserCallback) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        signInWithCredential(credential, callback)
    }

    override fun attemptSignInWithFacebook(token: AccessToken, callback: IFirebaseAuth.FirebaseUserCallback) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        signInWithCredential(credential, callback)
    }

    private fun signInWithCredential(credential: AuthCredential, callback: IFirebaseAuth.FirebaseUserCallback) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                callback.onSuccess(authResult.user)
            }
            .addOnFailureListener { exception ->
                when (exception) {
                    is FirebaseAuthInvalidUserException -> callback.onFailure("Невірний email або пароль")
                    is FirebaseAuthInvalidCredentialsException -> callback.onFailure("Невірний email або пароль")
                    else -> callback.onFailure("Помилка аутентифікації")
                }
            }
    }

    override fun createNewAccount(user: User, callback: IFirebaseAuth.FirebaseUserCallback) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { authResult ->
                val currentUser = authResult.user
                val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(user.fullName)
                    .setPhotoUri(user.photoUri)
                    .build()
                currentUser.updateProfile(userProfileChangeRequest)

                sendEmailVerification(currentUser, callback)
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseAuthUserCollisionException) {
                    if (exception.errorCode == "ERROR_EMAIL_ALREADY_IN_USE")
                        callback.onFailure("Користувач з таким e-mail вже зареєстрований")
                } else
                    callback.onFailure("Помилка створення облікового запису")
            }
    }

    fun sendEmailVerification(user: FirebaseUser, callback: IFirebaseAuth.FirebaseUserCallback) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful)
                callback.onSuccess(user)
            else
                callback.onFailure("Не вдалося відправити лист для підтвердження e-mail")
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser
    }

    override fun sendPasswordReset(email: String, callback: IFirebaseAuth.FirebaseCallback) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                callback.onSuccess()
            }
            .addOnFailureListener { callback.onFailure("Не вдалося відправити лист. Перевірте правильність e-mail") }
    }

    override fun signOut(callback: IFirebaseAuth.FirebaseCallback) {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            FirebaseAuth.getInstance().signOut()
            callback.onSuccess()
        } else
            callback.onFailure("Користувач не авторизований")
    }
}
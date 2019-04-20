package com.chupryna.socialapplication.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class FirebaseAuthorization : IFirebaseAuth {

    override fun attemptSignIn(email: String, password: String, callback: IFirebaseAuth.AuthorizationCallback) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSuccess()
               // val user = auth.currentUser
            } else {
               try {
                   throw task.exception!!
               } catch (e: FirebaseAuthInvalidUserException ) {
                   callback.onFailure("Невірний email або пароль")
               } catch (e: FirebaseAuthInvalidCredentialsException) {
                   callback.onFailure("Невірний email або пароль")
               } catch (e: Exception) {
                   callback.onFailure("Помилка аутентифікації")
               }
            }
        }
    }

    override fun createNewAccount(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
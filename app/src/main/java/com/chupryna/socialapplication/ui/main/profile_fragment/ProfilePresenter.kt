package com.chupryna.socialapplication.ui.main.profile_fragment

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.user.Geo
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.IUserDataSource
import com.chupryna.socialapplication.data.user.UserRepository
import com.stfalcon.imageviewer.StfalconImageViewer

class ProfilePresenter(private val view: IProfileView,
                       context: Context) {

    private val model by lazy { UserRepository(context) }

    fun showUserInfo(user: User) {
        view.setFullName(user.name)
        view.setUserName(user.username)
        view.setWebsite(user.website)
        view.setPhone(user.phone)
        view.setEmail(user.email)
        view.setAddress(String.format("%s, %s", user.address!!.city, user.address.street))
        view.setZipCode("Індекс: ${user.address.zipCode}")
        view.setCompany(user.company!!.name)
    }

    fun loadUser(id: Int) {
        view.showProgress()
        model.getUserById(id, object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                showUserInfo(list[0])
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }

    fun onEmail(email: String) {
        if (email.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            view.showActivity(intent)
        }
    }

    fun onPhone(phone: String) {
        if (phone.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            view.showActivity(intent)
        }
    }

    fun onAddress(geo: Geo?) {
        if (geo != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.apps.maps")
            intent.data = Uri.parse("geo:0,0?q=${geo.lat},${geo.lng}")
            view.showActivity(intent)
        }
    }

    fun onPhoto(context: Context) {
        StfalconImageViewer.Builder<Drawable>(context, listOf(context.getDrawable(R.drawable.profile))) { imageView, photo ->
            imageView.setImageDrawable(photo)
        }.show()
    }
}
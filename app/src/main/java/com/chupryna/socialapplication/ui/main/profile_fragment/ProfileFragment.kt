package com.chupryna.socialapplication.ui.main.profile_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment(private val user: User) : Fragment(), IProfileView {

    private val presenter by lazy { ProfilePresenter(this, context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (user.name.isEmpty() || user.address == null || user.email.isEmpty() && user.id != 0)
            presenter.loadUser(user.id)
        else
            presenter.showUserInfo(user)
        initListeners()
    }

    private fun initListeners() {
        emailProfileContainer.setOnClickListener { presenter.onEmail(emailProfileTv.text.toString()) }
        phoneProfileContainer.setOnClickListener { presenter.onPhone(phoneProfileTv.text.toString()) }
        addressProfileContainer.setOnClickListener { presenter.onAddress(user.address?.geo) }
        photoProfileIv.setOnClickListener { presenter.onPhoto(context!!) }
    }

    override fun setFullName(name: String) {
        fullNameProfileTv.text = name
    }

    override fun setUserName(name: String) {
        userNameProfileTv.text = name
    }

    override fun setWebsite(website: String) {
        websiteProfileTv.text = website
    }

    override fun setPhone(phone: String) {
        phoneProfileTv.text = phone
    }

    override fun setEmail(email: String) {
        emailProfileTv.text = email
    }

    override fun setCompany(company: String) {
        companyProfileTv.text = company
    }

    override fun setAddress(address: String) {
        addressProfileTv.text = address
    }

    override fun setZipCode(code: String) {
        zipCodeProfileTv.text = code
    }

    override fun showActivity(intent: Intent) {
        startActivity(Intent.createChooser(intent, "Завершити дію"))
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}

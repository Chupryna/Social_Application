package com.chupryna.socialapplication.ui.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class AuthorizationActivity : AppCompatActivity(), IAuthorizationView {

    private val presenter by lazy { AuthorizationPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onLoadActivity()
    }


    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    override fun showProgress() {
        authContainerProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        authContainerProgressBar.visibility = View.GONE
    }
}

package com.chupryna.socialapplication.ui.authorization

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class AuthorizationActivity : AppCompatActivity(), IAuthorizationView {

    val presenter by lazy { AuthorizationPresenter(this) }

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

    override fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun showToast(msg: String) {
       Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}

package com.chupryna.socialapplication.ui.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.ui.authorization.AuthorizationActivity
import com.chupryna.socialapplication.ui.main.album_fragment.AlbumFragment
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, IMainView {

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()
        initListeners()
    }

    private fun initView() {
        val user = intent.getParcelableExtra<FirebaseUser>("user")
        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.fullName_user_tv).text = user.displayName
        headerView.findViewById<TextView>(R.id.email_use_tv).text = user.email
    }

    private fun initListeners() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        val headerView = navigationView.getHeaderView(0)
        headerView.setOnClickListener { presenter.onProfile() }
    }

    override fun onBackPressed() {
        closeDrawer()
        super.onBackPressed()
    }

    override fun closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            //R.id.action_settings ->
            R.id.action_quit -> presenter.onQuit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {

            }
            R.id.nav_albums -> {
                presenter.onAlbums()
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_ontainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_ontainer, fragment)
            .commit()
    }

    override fun showProgress() {
        mainContainerProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mainContainerProgressBar.visibility = View.GONE
    }

    override fun loadAuthActivity() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}
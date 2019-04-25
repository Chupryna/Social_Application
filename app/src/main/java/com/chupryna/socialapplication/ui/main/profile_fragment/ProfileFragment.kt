package com.chupryna.socialapplication.ui.main.profile_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chupryna.socialapplication.R

class ProfileFragment : Fragment(), IProfileView {

    private val presenter by lazy { ProfilePresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }
}

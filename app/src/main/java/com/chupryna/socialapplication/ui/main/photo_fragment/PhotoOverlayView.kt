package com.chupryna.socialapplication.ui.main.photo_fragment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.utils.shareImage
import kotlinx.android.synthetic.main.overlay_photo.view.*

class PhotoOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.overlay_photo, this)
    }

    fun update(photo: Photo) {
        photoOverlayDescriptionTv.text = photo.title
        photoOverlayShareBtn.setOnClickListener { context.shareImage(photo.url) }
    }
}
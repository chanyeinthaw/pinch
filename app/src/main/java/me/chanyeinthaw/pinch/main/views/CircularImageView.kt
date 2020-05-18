package me.chanyeinthaw.pinch.main.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import me.chanyeinthaw.pinch.R

class CircularImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {
    lateinit var imageView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.circular_image_view, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        imageView = findViewById(R.id.imageViewCIV)
    }
}
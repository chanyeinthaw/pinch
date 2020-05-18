package me.chanyeinthaw.pinch.main.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import me.chanyeinthaw.pinch.R

class NavButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {
    private var icon: ImageView? = null
    private var text: TextView? = null

    private var label: String? = null
    private var isActive: Boolean = true

    init {
        LayoutInflater.from(context).inflate(R.layout.nav_button_view, this)
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.NavButton, 0, 0)

        isActive = attributes.getBoolean(R.styleable.NavButton_isActive, true)
        label = attributes.getString(R.styleable.NavButton_label)

        attributes.recycle()
    }

    private fun changeActiveState(state: Boolean) {
        icon?.visibility = if (state) View.VISIBLE else View.INVISIBLE
        text?.setTextColor(
            ResourcesCompat.getColor(
                resources, if (state) R.color.colorNavActive else R.color.colorNavInActive, null
            )
        )
    }

    private fun setUpViews() {
        icon = findViewById(R.id.navViewIcon)
        text = findViewById(R.id.navViewTextView)
    }

    private fun setupValuesWithAttr() {
        text?.text = label
        changeActiveState(isActive)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpViews()
        setupValuesWithAttr()
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        super.setOnClickListener {
            changeActiveState(true)
            listener?.onClick(it as NavButton)
            activateButtonAndDeactivateOthers(it as NavButton)
        }
    }

    companion object {
        private val buttons: ArrayList<NavButton> = arrayListOf()

        fun addToGroup(vararg navButtons: NavButton) {
            navButtons.forEach {
                buttons.add(it)
            }
        }

        fun activateButtonAndDeactivateOthers(button: NavButton?) {
            buttons.forEach {
                if (it != button) {
                    it.changeActiveState(false)
                } else {
                    button.changeActiveState(true)
                }
            }
        }
    }
}
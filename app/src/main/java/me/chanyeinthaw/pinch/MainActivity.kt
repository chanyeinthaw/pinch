package me.chanyeinthaw.pinch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import me.chanyeinthaw.pinch.customviews.NavButton
import me.chanyeinthaw.pinch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var navigator: MainNavigator
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyLayoutFullScreen()

        setUpNavControllers()

        bindEventListeners()

        Preference.initialize(getPreferences(Context.MODE_PRIVATE))
    }

    override fun onBackPressed() {
        navigator.handleBackFromHome(this) {
            super.onBackPressed()
        }
    }

    private fun setUpNavControllers() {
        val navControllerHome = Navigation.findNavController(
            this@MainActivity,
            R.id.navHostFragment
        )

        navigator = MainNavigator(navControllerHome)
        navigator.applyOnDestinationChangeListener { visibility, buttonIndex ->
            binding.groupNav.visibility = visibility
            val button = when(buttonIndex) {
                0 -> binding.buttonCouple
                1 -> binding.buttonStory
                else -> null
            }

            button?.let {
                NavButton.activateButtonAndDeactivateOthers(button)
            }
        }
    }

    private fun bindEventListeners() {
        NavButton.addToGroup(binding.buttonCouple, binding.buttonStory)

        binding.buttonCouple.setOnClickListener {
            navigator.navigateToCouple()
        }

        binding.buttonStory.setOnClickListener {
            navigator.navigateToStory()
        }
    }

    private fun applyLayoutFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

package me.chanyeinthaw.pinch.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.views.NavButton
import me.chanyeinthaw.pinch.databinding.ActivityMainBinding
import me.chanyeinthaw.pinch.main.routers.MainRouter


class MainActivity : AppCompatActivity() {
    private lateinit var mainRouter: MainRouter
    private lateinit var binding: ActivityMainBinding

    var navControllerSettings: NavController? = null
    var isSettingsAtSettingHome : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyLayoutFullScreen()

        setUpNavControllers()

        bindEventListeners()
    }

    private fun setUpNavControllers() {
        val navControllerHome = Navigation.findNavController(
            this@MainActivity,
            R.id.navHostFragment
        )

        mainRouter = MainRouter(navControllerHome)
        mainRouter.applyOnDestinationChangeListener { visibility, buttonIndex ->
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

        binding.iconSettings.setOnClickListener {
            mainRouter.goToSettings()
        }

        binding.buttonCouple.setOnClickListener {
            mainRouter.goToCouple()
        }

        binding.buttonStory.setOnClickListener {
            mainRouter.goToStory()
        }
    }

    private fun applyLayoutFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

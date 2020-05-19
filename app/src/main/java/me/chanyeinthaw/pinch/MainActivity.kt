package me.chanyeinthaw.pinch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import me.chanyeinthaw.pinch.customviews.NavButton
import me.chanyeinthaw.pinch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mainRouter: MainNavigator
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

        mainRouter = MainNavigator(navControllerHome)
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
            mainRouter.navigateToSettings()
        }

        binding.buttonCouple.setOnClickListener {
            mainRouter.navigateToCouple()
        }

        binding.buttonStory.setOnClickListener {
            mainRouter.navigateToStory()
        }
    }

    private fun applyLayoutFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

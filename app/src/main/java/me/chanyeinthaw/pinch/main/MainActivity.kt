package me.chanyeinthaw.pinch.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.views.NavButton
import me.chanyeinthaw.pinch.databinding.ActivityMainBinding
import me.chanyeinthaw.pinch.main.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {
    private lateinit var navControllerHome: NavController
    private lateinit var binding: ActivityMainBinding

    var settingFragmentCurrentDestination = SettingsFragment.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyLayoutFullScreen()

        setUpNavControllers()

        bindEventListeners()
    }

    override fun onBackPressed() {
        when(navControllerHome.currentDestination?.id) {
            R.id.settingsFragment -> {
                when(settingFragmentCurrentDestination) {
                    SettingsFragment.HOME -> handleSettingsHomeOnBackPressed(navControllerHome.previousBackStackEntry?.destination?.id)
                }
            }
            R.id.storyFragment -> {
                navControllerHome.navigate(R.id.action_storyFragment_to_coupleFragment)
            }
            else -> finish()
        }
    }

    private fun handleSettingsHomeOnBackPressed(destinationId: Int?) {
        val destination : Pair<Int, NavButton>? = when(destinationId) {
            R.id.coupleFragment -> Pair(R.id.action_settingsFragment_to_coupleFragment, binding.buttonCouple)
            R.id.storyFragment -> Pair(R.id.action_settingsFragment_to_storyFragment, binding.buttonStory)
            else -> null
        }

        destination?.let {
            navControllerHome.navigate(destination.first)
        }
    }

    private fun setUpNavControllers() {
        navControllerHome = Navigation.findNavController(
            this@MainActivity,
            R.id.navHostFragment
        )
    }

    private fun bindEventListeners() {
        NavButton.addToGroup(binding.buttonCouple, binding.buttonStory)

        binding.iconSettings.setOnClickListener(::onIconSettingsClick)
        binding.buttonCouple.setOnClickListener(::onButtonCoupleClick)
        binding.buttonStory.setOnClickListener(::onButtonStoryClick)
        navControllerHome.addOnDestinationChangedListener {
            _, destination, _ -> onNavigationDestinationChanged(destination)
        }
    }

    private fun onNavigationDestinationChanged(destination: NavDestination) {
        when(destination.id) {
            R.id.settingsFragment -> binding.groupNav.visibility = View.GONE
            else -> {
                binding.groupNav.visibility = View.VISIBLE
                when(destination.id) {
                    R.id.coupleFragment -> NavButton.activateButtonAndDeactivateOthers(binding.buttonCouple)
                    R.id.storyFragment -> NavButton.activateButtonAndDeactivateOthers(binding.buttonStory)
                }
            }

        }
    }

    private fun onIconSettingsClick(v: View?) {
        val destination : Int? = when(navControllerHome.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_settingsFragment
            R.id.storyFragment -> R.id.action_storyFragment_to_settingsFragment
            else -> null
        }

        destination?.let {
            navControllerHome.navigate(destination)
        }
    }

    private fun onButtonCoupleClick(v: View?) {
        val destination : Int? = when(navControllerHome.currentDestination?.id) {
            R.id.storyFragment -> R.id.action_storyFragment_to_coupleFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_coupleFragment
            else -> null
        }

        destination?.let {
            navControllerHome.navigate(it)
        }
    }

    private fun onButtonStoryClick(v: View?) {
        val destination : Int? = when(navControllerHome.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_storyFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_storyFragment
            else -> null
        }

        destination?.let {
            navControllerHome.navigate(it)
        }
    }

    private fun applyLayoutFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

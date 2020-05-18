package me.chanyeinthaw.pinch.main.routers

import android.view.View
import androidx.navigation.NavController
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.MainActivity

class MainRouter(var controller: NavController) {
    fun applyOnDestinationChangeListener(toggleViewGroupVisibility: (Int) -> Unit) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.settingsFragment -> toggleViewGroupVisibility(View.GONE)
                else -> toggleViewGroupVisibility(View.VISIBLE)
            }
        }
    }

    fun goToStory() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_storyFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_storyFragment
            else -> null
        }

        destination?.let {
            controller.navigate(it)
        }
    }

    fun goToCouple() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.storyFragment -> R.id.action_storyFragment_to_coupleFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_coupleFragment
            else -> null
        }

        destination?.let {
            controller.navigate(it)
        }
    }

    fun goToSettings() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_settingsFragment
            R.id.storyFragment -> R.id.action_storyFragment_to_settingsFragment
            else -> null
        }

        destination?.let {
            controller.navigate(destination)
        }
    }

    private fun handleBackFromSettingsHome(destinationId: Int?) {
        val destination = when(destinationId) {
            R.id.coupleFragment -> R.id.action_settingsFragment_to_coupleFragment
            R.id.storyFragment -> R.id.action_settingsFragment_to_storyFragment
            else -> null
        }

        destination?.let {
            controller.navigate(destination)
        }
    }

    fun handleBackFromHome(activity: MainActivity, superOnBackPressedCallback: () -> Unit) {
        when(controller.currentDestination?.id) {
            R.id.settingsFragment -> {
                when(activity.isSettingsAtSettingHome) {
                    true ->
                        handleBackFromSettingsHome(controller.previousBackStackEntry?.destination?.id)
                    else -> superOnBackPressedCallback()
                }
            }
            R.id.storyFragment -> {
                controller.navigate(R.id.action_storyFragment_to_coupleFragment)
            }
            else -> activity.finish()
        }
    }
}
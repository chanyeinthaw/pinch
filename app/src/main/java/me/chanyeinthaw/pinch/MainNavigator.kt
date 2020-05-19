package me.chanyeinthaw.pinch

import android.view.View
import androidx.navigation.NavController

class MainNavigator(var controller: NavController) {
    fun applyOnDestinationChangeListener(onDestinationChanged: (Int, Int?) -> Unit) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.settingsFragment -> onDestinationChanged(View.GONE, null)
                R.id.storyFragment -> onDestinationChanged(View.VISIBLE, 1)
                R.id.coupleFragment -> onDestinationChanged(View.VISIBLE, 0)
            }
        }
    }

    fun navigateToStory() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_storyFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_storyFragment
            else -> null
        }

        destination?.let {
            controller.navigate(it)
        }
    }

    fun navigateToCouple() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.storyFragment -> R.id.action_storyFragment_to_coupleFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_coupleFragment
            else -> null
        }

        destination?.let {
            controller.navigate(it)
        }
    }

    fun navigateToSettings() {
        val destination : Int? = when(controller.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_settingsFragment
            R.id.storyFragment -> R.id.action_storyFragment_to_settingsFragment
            else -> null
        }

        destination?.let {
            controller.navigate(destination)
        }
    }
}
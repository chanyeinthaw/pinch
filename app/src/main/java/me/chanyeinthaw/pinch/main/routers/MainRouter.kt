package me.chanyeinthaw.pinch.main.routers

import android.view.View
import androidx.navigation.NavController
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.MainActivity

class MainRouter(var controller: NavController) {
    fun applyOnDestinationChangeListener(onDestinationChanged: (Int, Int?) -> Unit) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.settingsFragment -> onDestinationChanged(View.GONE, null)
                R.id.storyFragment -> onDestinationChanged(View.VISIBLE, 1)
                R.id.coupleFragment -> onDestinationChanged(View.VISIBLE, 0)
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
}
package me.chanyeinthaw.pinch

import android.annotation.SuppressLint
import android.view.View
import androidx.navigation.NavController

class MainNavigator(var controller: NavController) {
    fun applyOnDestinationChangeListener(onDestinationChanged: (Int, Int?) -> Unit) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.editProfileFragment -> onDestinationChanged(View.GONE, null)
                R.id.storyFragment -> onDestinationChanged(View.GONE, 1)
                R.id.coupleFragment -> onDestinationChanged(View.GONE, 0)
            }
        }
    }

    fun navigateToStory() {
        controller.navigate(R.id.action_coupleFragment_to_storyFragment)
    }

    fun navigateToCouple() {
        controller.navigate(R.id.action_storyFragment_to_coupleFragment)
    }

    fun navigateToEditProfile() {
        controller.navigate(R.id.action_coupleFragment_to_editProfileFragment)
    }

    fun handleBackFromHome(activity: MainActivity, superOnBackPressedCallback: () -> Unit) {
        when(controller.currentDestination?.id) {
            R.id.storyFragment -> controller.navigate(R.id.action_storyFragment_to_coupleFragment)
            R.id.editProfileFragment -> controller.navigate(R.id.action_editProfileFragment_to_coupleFragment)
            else -> activity.finish()
        }
    }
}
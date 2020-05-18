package me.chanyeinthaw.pinch.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.views.NavButton
import me.chanyeinthaw.pinch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyLayoutFullScreen()

        navController = Navigation.findNavController(
            this@MainActivity,
            R.id.navHostFragment
        )

        NavButton.addToGroup(binding.buttonCouple, binding.buttonStory)

        binding.iconSettings.setOnClickListener(::onIconSettingsClick)
        binding.buttonCouple.setOnClickListener(::onButtonCoupleClick)
        binding.buttonStory.setOnClickListener(::onButtonStoryClick)
        navController.addOnDestinationChangedListener(::onNavigationDestinationChanged)
    }

    private fun onNavigationDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when(destination.id) {
            R.id.settingsFragment -> binding.groupNav.visibility = View.GONE
            else -> binding.groupNav.visibility = View.VISIBLE
        }
    }

    private fun onIconSettingsClick(v: View?) {
        val destination : Int? = when(navController.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_settingsFragment
            R.id.storyFragment -> R.id.action_storyFragment_to_settingsFragment
            else -> null
        }

        destination?.let {
            navController.navigate(destination)
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.settingsFragment) {
            val destination : Pair<Int, NavButton>? = when(navController.previousBackStackEntry?.destination?.id) {
                R.id.coupleFragment -> Pair(R.id.action_settingsFragment_to_coupleFragment, binding.buttonCouple)
                R.id.storyFragment -> Pair(R.id.action_settingsFragment_to_storyFragment, binding.buttonStory)
                else -> null
            }

            destination?.let {
                navController.navigate(destination.first)
            }
        }
        else finish()
    }

    private fun onButtonCoupleClick(v: View?) {
        val destination : Int? = when(navController.currentDestination?.id) {
            R.id.storyFragment -> R.id.action_storyFragment_to_coupleFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_coupleFragment
            else -> null
        }

        destination?.let {
            navController.navigate(it)
        }
    }

    private fun onButtonStoryClick(v: View?) {
        val destination : Int? = when(navController.currentDestination?.id) {
            R.id.coupleFragment -> R.id.action_coupleFragment_to_storyFragment
            R.id.settingsFragment -> R.id.action_settingsFragment_to_storyFragment
            else -> null
        }

        destination?.let {
            navController.navigate(it)
        }
    }

    private fun applyLayoutFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

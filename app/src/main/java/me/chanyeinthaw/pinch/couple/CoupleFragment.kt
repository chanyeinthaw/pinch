package me.chanyeinthaw.pinch.couple

import android.annotation.SuppressLint
import android.icu.text.DateIntervalInfo
import android.icu.util.DateInterval
import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import me.chanyeinthaw.pinch.MainActivity
import me.chanyeinthaw.pinch.MainNavigator
import me.chanyeinthaw.pinch.Preference
import me.chanyeinthaw.pinch.databinding.FragmentCoupleBinding
import me.chanyeinthaw.pinch.toFormattedString
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.ceil

class CoupleFragment : Fragment() {
    private lateinit var binding: FragmentCoupleBinding
    private lateinit var navigator: MainNavigator
    private lateinit var datePicker: MaterialDatePicker<*>
    private val data : Data = Data()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoupleBinding.inflate(inflater, container, false)
        navigator = (activity as MainActivity).navigator

        initializeValues()
        initializeDatePicker()
        bindEventListeners()

        return binding.root
    }

    private fun initializeDatePicker() {
        val builder : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        datePicker = builder.build()
    }

    private fun bindEventListeners() {
        binding.imageViewHoney.setOnClickListener {
            navigator.navigateToEditProfile()
        }

        binding.imageViewBabe.setOnClickListener {
            navigator.navigateToEditProfile()
        }

        binding.textViewTotalDays.setOnClickListener(::showDatePicker)
        binding.textViewFooter.setOnClickListener(::showDatePicker)
        binding.textViewFooter.setOnClickListener(::showDatePicker)


        datePicker.addOnPositiveButtonClickListener(::onDateSelect)
    }

    private fun showDatePicker(view: View) {
        activity?.supportFragmentManager?.let {
            if (!datePicker.isVisible)
                datePicker.show(it, datePicker.toString())   // 3
        }
    }

    private fun onDateSelect(date: Any) {
        Preference.startDate = date as Long

        initializeValues()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeValues() {

        binding.textViewDate.text = data.upcomingTotalDaysDate.toFormattedString()
        binding.textViewDaysBeforeNumber.text = data.daysBeforeUpcomingTotalDays.toString()
        binding.textViewDaysNumber.text = data.upcomingTotalDays.toString()
        binding.textViewTotalDays.text = "${data.totalDays} D"
        binding.textViewFooter.text = data.startDate.toFormattedString()

        binding.textViewHoney.text = "Chan"
        binding.textViewBabe.text = "Nyein"
    }

    class Data {
        var startDate : Date = Date()
            private set
            get() = Date(Preference.startDate ?: 0)

        var totalDays : Long = 0
            private set
            get() {
                val diff = Date().time - startDate.time

                return (diff / (1000 * 3600 * 24)) + 1
            }

        var upcomingTotalDays : Long = 0
            get() = (ceil(totalDays/100.0) * 100).toLong()
            private set

        var daysBeforeUpcomingTotalDays : Long = 0
            get() = upcomingTotalDays - totalDays
            private set

        var upcomingTotalDaysDate : Date = Date()
            get() {
                val millis = daysBeforeUpcomingTotalDays * 24 * 3600 * 1000

                return Date(startDate.time + millis)
            }
            private set
    }
}
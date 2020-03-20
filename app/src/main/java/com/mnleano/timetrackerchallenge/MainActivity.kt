package com.mnleano.timetrackerchallenge

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mnleano.timetrackerchallenge.databinding.ActivityMainBinding
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: InputsAdapter
    private val dateTimes: ArrayList<DateTime> = arrayListOf()

    private var selectedYear: Int = 0
    private var selectedMonth: Int = 0
    private var selectedDay: Int = 0
    private var startHour: Int = 0
    private var startMinute: Int = 0
    private var endHour: Int = 0
    private var endMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar.toolbar)

        adapter = InputsAdapter(dateTimes)
        binding.rvInputs.adapter = adapter
    }

    fun onAddClick(v: View) {
        selectDate()

    }

    private fun selectDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                Timber.d("OnDateSetListener: year=$year, month=$month, dayOfMonth=$dayOfMonth")
                selectedYear = year
                selectedMonth = month
                selectedDay = dayOfMonth
                selectTime()
            }, year, month, day
        )
        datePicker.setTitle(getString(R.string.select_date))
        datePicker.show()

    }

    private fun selectTime(count: Int = 0) {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                Timber.d("OnTimeSetListener: hourOfDay=$hourOfDay, minute=$minute")
                if (count == 0) {
                    startHour = hourOfDay
                    startMinute = minute
                    selectTime(1)
                } else {
                    endHour = hourOfDay
                    endMinute = minute
                    addToList()
                }
            },
            hour,
            minute,
            false
        )
        timePicker.setTitle(getString(if (count == 0) R.string.select_start_time else R.string.select_end_time))
        timePicker.show()
    }

    private fun addToList() {
        val calendar = Calendar.getInstance()
        calendar.set(selectedYear, selectedMonth, selectedDay, startHour, startMinute)
        val startDate = calendar.time
        calendar.set(selectedYear, selectedMonth, selectedDay, endHour, endMinute)
        val endDate = calendar.time
        dateTimes.add(DateTime(startDate, endDate))
        adapter.notifyDataSetChanged()

        binding.btnGenerate.isEnabled = true
        binding.btnClear.isEnabled = true

    }

    fun onClearClick(v: View) {
        dateTimes.clear()
        adapter.notifyDataSetChanged()

        binding.btnGenerate.isEnabled = false
        binding.btnClear.isEnabled = false

    }

    fun onGenerateClick(v: View) {
        startActivity(OutputActivity.makeIntent(this, dateTimes))
    }
}

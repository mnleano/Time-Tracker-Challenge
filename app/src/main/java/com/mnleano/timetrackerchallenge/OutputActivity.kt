package com.mnleano.timetrackerchallenge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class OutputActivity : AppCompatActivity() {

    private var dateTimes: ArrayList<DateTime> = arrayListOf()
    private var dates= mutableSetOf<DateTime>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        initData()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initData() {
        intent.extras?.let {
            dateTimes = it.getSerializable(KEY_DATE_TIME) as ArrayList<DateTime>
            for (date in dateTimes) {
                Timber.d("initData: startDate: ${date.startDate}, endDate: ${date.endDate}")
                dates.add(date)
            }
        }


        for(date in dates){
            Timber.d("initData: Unique date: ${date.startDate}")
        }
    }

    companion object {
        private const val KEY_DATE_TIME = "dateTime"

        fun makeIntent(context: Context, dateTimes: ArrayList<DateTime>): Intent {
            val intent = Intent(context, OutputActivity::class.java)
            intent.putExtra(KEY_DATE_TIME, dateTimes)
            return intent
        }
    }

}

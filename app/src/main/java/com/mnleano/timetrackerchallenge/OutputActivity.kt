package com.mnleano.timetrackerchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OutputActivity : AppCompatActivity() {

    companion object {
        const val KEY_DATE_TIME = "dateTime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)
    }
}

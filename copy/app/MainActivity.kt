package com.childmathematics.android.shiftcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.compose.setContent
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import androidx.compose.runtime.Composable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            MainScreen()

        }
    }
    @Composable
    fun MainScreen() {
        StaticCalendar()
    }
}
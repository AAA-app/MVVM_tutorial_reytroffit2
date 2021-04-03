package com.aaa.mvvm_tutorial_reytroffit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        tvWelcome.text = "Welcome ${username}, you entered ${password}"
    }
}
package com.aaa.mvvm_tutorial_reytroffit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (TextUtils.isEmpty(username)) {
                usernameInput.error = "Please enter username"
            } else if (TextUtils.isEmpty(password)) {
                passwordInput.error = "Please enter password"
            } else {

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
                finish()
            }

        }
    }
}
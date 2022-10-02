package com.miminerica.apipractice

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContent = this

    abstract fun setupEvent()
    abstract fun setValue()
}
package com.devtides.coroutinesroom.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.devtides.coroutinesroom.R

//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
class MainActivity : AppCompatActivity() {
    /*objective:
    * Add coroutine to access Room database in background thread without affecting UI */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}

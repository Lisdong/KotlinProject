package com.example.kt.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kt.R.layout.home_layout
import com.example.kt.java.TestJavaDemo
import kotlinx.android.synthetic.main.home_layout.*

/**
 * Created By LRD
 * on 2018/8/6  notes：
 */
class HomeActivity : AppCompatActivity() {
    var demo = TestJavaDemo();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(home_layout)


        home_text.text = demo.type+"--"+demo.kotlinType;
    }
}
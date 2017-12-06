package com.example.xiejin.kotlinlearning

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        printAll(3, 2, 2, 1, 2, 3, 4)
    }

    fun sum(fuck1: Int, fuck2: Int): Int = fuck1 + fuck2;

    fun printAll(vararg v: Int) {
        for (vv in v) {
            Log.d("fck", "" + vv)
        }
    }
}

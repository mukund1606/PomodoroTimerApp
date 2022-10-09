package com.example.pomodorotimer

import android.os.CountDownTimer
import androidx.compose.runtime.Composable

var value: String = "";
fun timer(time: Long){
    var timer = object: CountDownTimer(time*1000, 1000){
        override fun onTick(p0: Long) {
            println(p0/1000);
            value = ((p0/1000) + 1).toString()
        }
        override fun onFinish() {
            println("Hello");
        }
    }
    timer.start()
}
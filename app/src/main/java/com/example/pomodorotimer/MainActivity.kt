package com.example.pomodorotimer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodorotimer.ui.theme.PomodoroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun Inner_Circle(){
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(color = Color(254, 142, 142), shape = CircleShape)
            .shadow(shape = CircleShape, elevation = 0.dp, clip = false),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(R.drawable.favpng_tomato_clip_art), contentDescription = "Tomato",
        modifier = Modifier.size(150.dp))

    }
}

@Composable
fun Start_Button(time: Long) {
    var text by remember {
        mutableStateOf("START")
    }
    var state by remember {
        mutableStateOf(true)
    }
    if (state) {
        Button(
            onClick = {
                state = false
                val timer = object : CountDownTimer(time * 1000, 1000) {
                    override fun onTick(p0: Long) {
                        text = ((p0 / 1000) + 1).toString()
                    }

                    override fun onFinish() {
                        text = "Finished"
                        state = true
                    }
                }
                timer.start()
            },
            modifier = Modifier
                .width(200.dp)
                .height(75.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(254, 142, 142)),

            ) {
            Text(
                text = text,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
    else{
        Button(
            onClick = {
                state = false
                val timer = object : CountDownTimer(time * 1000, 1000) {
                    override fun onTick(p0: Long) {
                        text = ((p0 / 1000) + 1).toString()
                        print(text)
                    }

                    override fun onFinish() {
                        text = "Finished"
                        state = true
                    }
                }
                timer.start()
            },
            modifier = Modifier
                .width(200.dp)
                .height(75.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(254, 142, 142)),
            enabled = false

            ) {
            Text(
                text = text,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var time by remember {
        mutableStateOf(TextFieldValue(""))
    }
    PomodoroTimerTheme {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFFF6262)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Inner_Circle()
            Spacer(modifier = Modifier.size(25.dp))
            TextField(value = time, onValueChange = {newText -> time = newText;},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.size(25.dp))
            if(time.text != ""){
                Start_Button(time.text.toLong())
            }
            else{
                Start_Button(0)
            }
        }
    }
}
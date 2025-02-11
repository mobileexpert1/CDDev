package com.composetut.customView

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.Duration

    @Composable
    fun CustomToast(
        message: String,
        duration: Int = Toast.LENGTH_LONG,
        showToast:Boolean=false
    ) {
        val context = LocalContext.current
        val showToast = remember { mutableStateOf(showToast) }
        println("Show Toast triggered CustomToast ${ showToast.value}")

        if (showToast.value) {
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                ){
                Card(modifier=Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    shape = RoundedCornerShape(1.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Gray),
                ){
                   Text(
                       text = message,
                       style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black),
                       modifier = Modifier
                           .background(Color.Transparent)
                           .align(alignment = Alignment.CenterHorizontally)
                           .padding(vertical = 5.dp, horizontal = 20.dp)
                       )
                }
            }

            LaunchedEffect(Unit){
                delay(if (duration==Toast.LENGTH_SHORT) 2000L else 3000L)
               // showToast.value=false
            }

        }


    }
    @Preview(showBackground = true)
    @Composable
    fun PreviewCustomToast() {
        CustomToast(
            message = "This is a custom toast!",
            duration = Toast.LENGTH_SHORT,
           true
        )
}
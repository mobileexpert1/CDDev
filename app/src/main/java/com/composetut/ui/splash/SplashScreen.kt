package com.composetut.ui.splash

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.mandatorySystemGesturesPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetut.R
import com.composetut.nav.NavigationItem
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navHost: NavHostController){
    Scaffold(
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.primary)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
               Box(modifier = Modifier.align(Alignment.CenterHorizontally).wrapContentSize()) {
                   Image(
                       painterResource(R.drawable.ic_password_toggle),
                       contentDescription = "coose image",
                       modifier = Modifier
                           .size(80.dp).padding(5.dp)
                   )
               }

                Text("Welcome to Eara",
                    modifier = Modifier.wrapContentSize()
                        .padding(vertical = 10.dp, horizontal = 5.dp)
                        .clickable { Log.e("welssssd","Welcome to splash") },
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp, color = Color.White)
                )
            }
        }
    )

    LaunchedEffect(Unit){
        Log.e("stateapp","Splash resumed")
        delay(3000L)
        navHost.navigate(NavigationItem.LOGIN.route)
    }
    DisposableEffect(Unit){
        onDispose {
            Log.e("stateapp","Splash paused")
        }
    }


}
@Preview(showBackground = true)
@Composable
fun previewSplash(){
    SplashScreen(rememberNavController())
}
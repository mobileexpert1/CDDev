package com.composetut.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetut.R
import com.composetut.data.KoinCompo
import com.composetut.data.MyPreference
import com.composetut.data.MyResults
import com.composetut.data.PreferenceKey
import com.composetut.nav.NavigationItem
import com.composetut.ui.login.model.LoginRequest
import com.composetut.ui.login.viewModel.LoginVm
import kotlinx.coroutines.Job


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navHost: NavHostController) {
    val rememberToast = remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("Please enter a valid 10-digit phone number.") }
    val isError = textState.value.length == 10 && textState.value.all { it.isDigit() }
    val loginvm:LoginVm = KoinCompo().loginVM
    val loginState by loginvm.loginLive.collectAsState()
    var hasNavigated = remember { mutableStateOf(false) }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.primary))
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = textState.value,
                    label = {
                        Text(
                            "Enter Phone", modifier = Modifier.wrapContentSize(),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                color = colorResource(R.color.hint_color)
                            )
                        )
                    },
                    isError = isError,
                    onValueChange = { newValue -> textState.value = newValue },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .border(
                            width = 1.dp, color = Color.White,
                            RoundedCornerShape(5.dp)
                        ),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black)
                    ),
                    maxLines = 1,
                )
                if (!isError) {
                    Text(
                        errorMessage.value,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        val inputText = textState.value
                        println("User input: $inputText")
                    }, modifier = Modifier.clip(RoundedCornerShape(5.dp))
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(
                            width = 1.dp, color = Color.White,
                            RoundedCornerShape(5.dp)
                        )
                        .background(colorResource(id = R.color.white)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white) // Set button color
                    )
                ) {
                    Text(
                        "Login",
                        modifier = Modifier
                            .clickable {
                                val data = LoginRequest(
                                    "appsdev096@gmail.com",
                                    "Appsdev096#")
                                hasNavigated.value=true
                                loginvm.fetchData(data)
                            },
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }


            }

            when(loginState){
                is MyResults.IsLoading->{}
                is MyResults.IsSuccess->{
                    if (hasNavigated.value) {
                        loginState.data?.run {
                            val token = response.accessToken
                            Log.e("mycopose", "result $this")
                            MyPreference.saveValue(PreferenceKey.TOKEN, token)
                            navHost.navigate(NavigationItem.HOME.route)
                            hasNavigated.value=false
                        }
                    }
                }
                is MyResults.IsError->{}
                else->{}
            }

//          if (remberToast.value) {
//              CustomToast("Welcome to the splash", Toast.LENGTH_LONG, true)
//              LaunchedEffect(Unit){
//              delay(3000L)
//              remberToast.value=false
//          }


            //  }
        }
    )

    LaunchedEffect(Unit) {
        Log.e("stateapp", "Login resumed")
        rememberToast.value = true
        MyPreference.clear()

    }
    DisposableEffect(Unit) {
        onDispose {
            Log.e("stateapp", "Login paused")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewSplash() {
    LoginScreen(rememberNavController())
}
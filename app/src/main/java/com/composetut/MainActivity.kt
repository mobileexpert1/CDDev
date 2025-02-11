package com.composetut

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.composetut.customView.CustomToast
import com.composetut.nav.AppNavigation
import com.composetut.ui.theme.ComposeTutTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutTheme {
                AppNavigation()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MessageCard(MessageData("Android", "Kotlin"))
//                }
            }
        }
    }
}

data class MessageData(var userName: String, var userId: String)


@Composable
fun MessageCard(data: MessageData) {
    val showToast = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Box(modifier = Modifier.wrapContentSize().height(50.dp)) {
            Image(
                painterResource(R.drawable.ic_app_logo),
                contentDescription = "My Image",
                modifier = Modifier.size(40.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color(R.color.black), RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .clickable {
                        coroutineScope.launch {

                            withContext(Dispatchers.Main) {
                                showToast.value = true
                                Toast.makeText(
                                    context,
                                    "Hi, good morning Android",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }


                    },
            )
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "coose image",
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.BottomCenter)
                    .size(20.dp)
                    .border(width = 1.dp, color = Color.Red, CircleShape)
                    .clickable {
                        showToast.value=true
                    },
                   colorFilter = ColorFilter.tint(color = Color.Black)
                )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier.padding(bottom = 50.dp),
        ) {
            Text(
                text = "User Name: ${data.userName}",
                color = Color.Red,
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.align(Alignment.Start)) {
                Box(modifier = Modifier.width(200.dp).height(50.dp)
                        .background(Color(R.color.teal_700)))
                {
                    Text(
                        text = "User Id: ${data.userId}",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


    }

    if (showToast.value) {
        println("Show Toast triggered ${showToast.value}")
        CustomToast("Hi good morning", duration = Toast.LENGTH_SHORT, showToast.value)
        LaunchedEffect(Unit) {
            delay(3000L)
            showToast.value = false
        }

    }
}

@Preview(showBackground = true)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_MASK,
//    showBackground = true,
//    name = "Dark Mode"
//)
@Composable
fun GreetingPreview() {
    ComposeTutTheme {
        // Greeting("Android")
        MessageCard(MessageData("Android", "Kotlin"))
    }
}



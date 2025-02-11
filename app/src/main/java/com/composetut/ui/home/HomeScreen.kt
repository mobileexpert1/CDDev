package com.composetut.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.composetut.R
import com.composetut.data.KoinCompo
import com.composetut.data.MyResults
import com.composetut.ui.dialogs.ShowAlertDialog
import com.composetut.ui.home.model.ProjectRequest
import com.composetut.ui.home.model.ResponseItem
import kotlin.math.ceil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val homeVm: HomeVm = KoinCompo().homeVm
    val homeState by homeVm.stateFlow.collectAsState()
    val rememberList = remember { mutableStateOf(ArrayList<ResponseItem>()) }
    val isLoaded= remember { mutableStateOf(true) }
    var showDialog= remember { mutableStateOf(false) }
    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.primary))
                    .padding(10.dp)
            ) {
                Column {
                    Text(
                        "Welcome to Home screen",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .clickable {
                                showDialog.value=true

                                // rememberList.value.addAll(addMoreItems(rememberList.value))
                            },
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    verticalList(rememberList.value) {
                        Log.e("itemclicke", "$it  ${rememberList.value.size}")
                        rememberList.value = ArrayList(rememberList.value).apply {
                            add(ResponseItem(name = "New Data"))
                        }
                    }
                }
            }

            when (homeState) {
                is MyResults.IsLoading -> {}
                is MyResults.IsSuccess -> {
                    Log.e("homeresults", "home results ${homeState.data?.response}")
                    if (isLoaded.value) {
                        homeState.data?.run {
                            val projectList = response
                            rememberList.value = projectList as ArrayList<ResponseItem>
                        }
                        isLoaded.value=false
                    }
                }

                is MyResults.IsError -> {}
                else -> {}
            }

            if (showDialog.value){
                ShowAlertDialog(showDialog.value){
                    showDialog.value=false
                }
            }
        }
    )
    LaunchedEffect(Unit) {
        homeVm.getDetails(ProjectRequest("+91 78945-61230"))

    }

}

@Composable
fun verticalList(list: ArrayList<ResponseItem>, callBack: (String) -> Unit) {
    Log.e("homeresults", "vertical list $list")
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize()
    ) {
        items(list)
        { item ->
            Box(
                modifier = Modifier.size(50.dp)
                    .clip(CircleShape)
                    .padding(5.dp)
                    .background(colorResource(id = R.color.black))
                    // .border(width = 1.dp, color = Color.White, RoundedCornerShape(10))
                    .clickable {
                        callBack.invoke(item.name)
                        item.name = "Raju"
                       if (item.isVisible)
                        item.isVisible=false
                        else item.isVisible=true
                    }

            ) {
                Image(painterResource(R.drawable.ic_password_toggle),
                    contentDescription = "product itme",
                    modifier = Modifier.run {
                        clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)).fillMaxWidth()
                            .height(30.dp)
                    }
                )

                Text(
                    item.name,
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Center)
                        .alpha(if (item.isVisible) 1f else 0f),
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Red)
                )
            }
        }
    }
}



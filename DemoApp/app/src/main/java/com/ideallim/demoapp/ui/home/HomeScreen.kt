package com.ideallim.demoapp.ui.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ideallim.demoapp.ui.bottomnav.BottomNavType
import com.ideallim.demoapp.ui.demoapps.youtube.YoutubeHomeActivity

@Composable
fun SelectScreen(screenType: BottomNavType) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose Test Application") },
                elevation = 8.dp
            )
        },
        content = {
            when (screenType){
                BottomNavType.HOME -> HomeScreenContent()
                BottomNavType.Second -> SecondScreenContent()
                BottomNavType.Third -> ThirdScreenContent()
            }
        },
        modifier = Modifier.background(Color.Green)
    )
}

@Composable
fun HomeScreenContent(
) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(){
            Button(
                onClick = { context.startActivity(YoutubeHomeActivity.newIntent(context)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = "youtube app",
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

    }
}



@Composable
fun SecondScreenContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)) {
    }
}

@Composable
fun ThirdScreenContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {
    }
}
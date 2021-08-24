package com.ideallim.demoapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ideallim.demoapp.ui.bottomnav.BottomNavType

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
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
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
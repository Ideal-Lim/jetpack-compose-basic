package com.ideallim.demoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ideallim.demoapp.ui.bottomnav.BottomNavType
import com.ideallim.demoapp.ui.home.SelectScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseView()
        }
    }
}

@Composable
fun BaseView(){
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }

    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationContent(
            modifier = Modifier
//                .semantics { contentDescription = bottomNavBarContentDescription }
            ,
            homeScreenState = homeScreenState
        )
    }
}

// Screen 바꿈.
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    modifier: Modifier
) {
    Column(modifier = modifier){
        Crossfade(homeScreen) { screenType ->
            SelectScreen(screenType)
        }
    }
}


//BottomNav 아이템 Icon 및 label 설정.
@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    BottomNavigation(modifier = modifier.background(Color.Green)) {
        BottomNavigationItem(
            icon = {
                Icon(Icons.Filled.Home, null)

            },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
            },
            label = { Text(text = stringResource(id = R.string.Home)) }
        )

        BottomNavigationItem(
            icon = {
               Icon(Icons.Filled.Today, null)
            },
            selected = homeScreenState.value == BottomNavType.Second,
            onClick = {
                homeScreenState.value = BottomNavType.Second
            },
            label = { Text(text = stringResource(id = R.string.Second)) }

        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Filled.Add, null)
            },
            selected = homeScreenState.value == BottomNavType.Third,
            onClick = {
                homeScreenState.value = BottomNavType.Third
            },
            label = { Text(text = stringResource(id = R.string.Third)) }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseView()
}
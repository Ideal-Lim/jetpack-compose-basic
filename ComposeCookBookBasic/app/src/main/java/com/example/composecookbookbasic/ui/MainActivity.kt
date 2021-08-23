package com.example.composecookbookbasic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.composecookbookbasic.R
import com.example.composecookbookbasic.ui.BottomNavType
import com.example.composecookbookbasic.ui.screen.HomeScreen
import com.example.composecookbookbasic.ui.utils.TestTags

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MainAppContent{ onBackPressed() }
            }
        }
    }

@Composable
fun MainAppContent(onBack : () -> Unit){
    //초깃값으로 Home으로 설정
    val homeScreenState = remember { mutableStateOf(BottomNavType.Home)}

    Column() {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            modifier = Modifier.weight(1f),
            onBack = onBack
        )
        //BottomNavigationContent
        BottomNavContent(homeScreenState = homeScreenState, modifier = Modifier.background(Color(R.color.white)))
    }
}

@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    modifier: Modifier,
    onBack: () -> Unit
){
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = Color(R.color.white)) {
                HomeScreen(screen,onBack)
            }
        }
    }
}

@Composable
fun BottomNavContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
){
    BottomNavigation(modifier = modifier){
        BottomNavigationItem(
            icon = {
                BottomNavImageIcon(painter = painterResource(id = R.drawable.home))
            },
            selected = homeScreenState.value == BottomNavType.Home,
            onClick = { homeScreenState.value = BottomNavType.Home},
            label = { Text(text = stringResource(id = R.string.navigation_item_home))},
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        BottomNavigationItem(
            icon = {
                BottomNavImageIcon(painter = painterResource(id = R.drawable.home))
            },
            selected = homeScreenState.value == BottomNavType.B,
            onClick = { homeScreenState.value = BottomNavType.B},
            label = { Text(text = stringResource(id = R.string.navigation_b))},
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        BottomNavigationItem(
            icon = {
                BottomNavImageIcon(painter = painterResource(id = R.drawable.home))
            },
            selected = homeScreenState.value == BottomNavType.C,
            onClick = { homeScreenState.value = BottomNavType.C},
            label = { Text(text = stringResource(id = R.string.navigation_c))},
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )
        BottomNavigationItem(
            icon = {
                BottomNavImageIcon(painter = painterResource(id = R.drawable.home))
            },
            selected = homeScreenState.value == BottomNavType.D,
            onClick = { homeScreenState.value = BottomNavType.D},
            label = { Text(text = stringResource(id = R.string.navigation_d))},
            modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
        )

    }
}

@Composable
fun BottomNavImageIcon(painter: Painter) {
    Icon(
        painter = painter,
        contentDescription = null
    )
}



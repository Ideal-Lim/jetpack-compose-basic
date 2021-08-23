package com.example.composecookbookbasic.ui.firstactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composecookbookbasic.ui.theme.ComposeCookBookBasicTheme
import com.example.composecookbookbasic.ui.theme.uiextensions.horizontalGradientBackground


class FirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          setContent{
              ComposeCookBookBasicTheme {
                  FirstHomeScreen()
              }
          }
    }
}

@Composable
fun FirstHomeScreen(){
    Scaffold {
        FirstScreen()
    }
}

@Composable
fun FirstScreen(){
    val scrollState = rememberScrollState(0)
    val gradientBackground = listOf(Color.White,Color.LightGray)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(gradientBackground) // 배경화면 그라데이션
    ){
        Text(
            text = "Compose UI Test App",
            style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxSize()
                .alpha(1f - scrollState.value / 200) // 스크롤할 때 고정되는 부분의 영역
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ){
            Spacer(modifier = Modifier.height(150.dp))
            Column(modifier = Modifier.horizontalGradientBackground(gradientBackground)){
                CategoryGrid()
            }
        }
    }
}


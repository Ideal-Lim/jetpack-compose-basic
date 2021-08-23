package com.example.composeuibasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeuibasic.ui.theme.ComposeUIBasicTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUIBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(name = "IdeaLim")
                }
            }
        }
    }
}

// @Composable : compose view를 반환 / view
@Composable
fun Greeting(name: String) {
    //Scaffold : 기본 메터리얼 디자인 Drawer, FloatingActionButton, TopApp Bar 등 다양한 컴포저블을 제공
    Scaffold(
        //TopBar
        topBar = {
        TopAppBar(title = {Text("Compose AppBar")},
        backgroundColor = Color(0xffE5FBB8)) },
        //FloatingActionButton
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Text("클릭")
            } },
        )
    
    // TextBlock
    {
        //Text(text = "Hello $name!") // ctrl + B 로 Modifier 확인 가능

        // MyComposableView 추가 (View 안의 View)
        MyComposableView()
    }
}

@Composable
fun MyRow(){
    Row(
        Modifier
            .padding(10.dp)
            .background(Color(0xff628395)),
        //gravity
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "안녕하세요",
            modifier = Modifier
                .background(Color(0xffF9F9F9))
                .padding(10.dp),
            color = Color(0xffD83A56))

        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "Hello",
            modifier = Modifier.padding(10.dp))
    }
}
//스택 Column, Row, Box(겹치기)
@Composable
fun MyComposableView() {
    Log.d("Tag", "MyComposableView:")

    Column(
        Modifier
            .background(Color(0xffC0FEFC))
            .verticalScroll(rememberScrollState()) // 스크롤 가능하게..
    ){
        // MyRow() 20개 생성
        for (i in 1..20){
            MyRow()
        }
    }
}

//미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUIBasicTheme {
        Greeting("Android")
    }
}
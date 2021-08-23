package com.example.composecookbookbasic.ui.screen



import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecookbookbasic.ui.BottomNavType
import com.example.composecookbookbasic.ui.ListActivity
import com.example.composecookbookbasic.ui.SecondActivity
import com.example.composecookbookbasic.ui.utils.TestTags

@Composable
fun HomeScreen(homeScreen: BottomNavType, onBack: () -> Unit) {

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.testTag(TestTags.HOME_SCREEN_ROOT),
        topBar = {
            TopAppBar(
                title = { Text(text = "MainActivity") },
                elevation = 18.dp,
                //왼쪽 Naviagation Icon
                navigationIcon = {
                  IconButton(
                      onClick = onBack
                  ) {
                      // Material.Icons.ArrowBack : ImageVector 로 받음
                      Icon(Icons.Filled.ArrowBack, contentDescription = null)
                  }
                },
                // actions -> icon 버튼 추가
                actions = {
                    //IconButton 이다.
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Search Button Clicked", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null
                        )
                    }
                }

            )
        },
        content = {
            HomeScreenContent(homeScreen)
        }
    )
}

@Composable
fun HomeScreenContent(homeScreen: BottomNavType){
    Box(
        modifier = Modifier.fillMaxSize(), Alignment.Center
    ){
        when(homeScreen){
            BottomNavType.Home -> StartActivity()
            BottomNavType.B -> Text( text = "B", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            BottomNavType.C -> Text( text = "C", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            else -> Text( text = "D", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun StartActivity(){
    val context = LocalContext.current
    val secondIntent = Intent(context,SecondActivity::class.java).apply {
        putExtra("key", "value")
    }
    val listIntent = Intent(context,ListActivity::class.java)
    Column(){
        Button(
            onClick = {
                context.startActivity(secondIntent)
            },
            modifier = Modifier.padding(vertical = 5.dp)
        ){
            Text( text = "StartActivity", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Button(onClick = {
            context.startActivity(listIntent)
        }) {
            Text( text = "ListActivity", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }
}

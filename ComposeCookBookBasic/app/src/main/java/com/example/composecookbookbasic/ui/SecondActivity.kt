package com.example.composecookbookbasic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class SecondActivity : ComponentActivity() {

    private val key: String by lazy {
        intent?.getStringExtra("key") ?: "key is null"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SecondScreen(key = key){
                onBackPressed()
            }
        }
    }
}

@Composable
fun SecondScreen(key: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "SecondActivity") },
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
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
                Text( text = key, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
    )
}
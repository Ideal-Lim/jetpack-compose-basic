package com.ideallim.demoapp.ui.demoapps.youtube

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class YoutubeHomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{

        }

    }


    companion object{
        fun newIntent(context: Context) =
            Intent(context, YoutubeHomeActivity::class.java)
//                .apply {  }
    }
}
package com.example.composeuibasic

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.composeuibasic.ui.theme.ComposeUIBasicTheme
import com.example.composeuibasic.ui.theme.TopBarColor
import com.example.composeuibasic.utils.DummyDataProvider
import com.example.composeuibasic.utils.RandomUser


class LazyColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUIBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ContextLayout()
                }
            }
        }
    }
}

@Composable
fun ContextLayout() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                AppBar()
            }
        ) {
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
    }

}

@Composable
fun AppBar(){
    TopAppBar(
        title = { Text("Lazy Column", fontSize = 20.sp) },
        backgroundColor = TopBarColor,
        elevation = 10.dp,
        modifier = Modifier.height(58.dp)
        )
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>){
    //메모리 관리하는 LazyColumn (RecyclerView 와 비슷)
    LazyColumn(){
        // randomUsers 의 리스트 데이터 하나 씩 가져옴
        items(randomUsers){ randomUser ->
            RandomUserView(randomUser) // RandomUserView(it) 으로 간략히 쓸 수 있음.
        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser){
    //cardView
    Card(modifier = Modifier
        .fillMaxWidth() //  match_parent
        .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
        ) {
        RandomUserCardView(randomUser = randomUser)
    }
}

@Composable
fun RandomUserCardView(randomUser: RandomUser){
    Row(modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically  // 세로로 Card 안에 있는 View 를 가운데 정렬
    ){
//        Box(modifier = Modifier
//            .size(width = 50.dp, height = 50.dp)
//            .clip(CircleShape)
//            .background(TopBarColor)
//            )
        RandomUserProfileImage(imgUrl = randomUser.profileImage)
        RandomUserNameAndDescription(randomUser = randomUser)
    }
}

@Composable
fun RandomUserNameAndDescription(randomUser: RandomUser){
    Column(
        modifier =  Modifier.padding(10.dp)
    ) {
        // custom style 은 Theme > typography > subtitle1
        Text(text = randomUser.name, style = MaterialTheme.typography.subtitle1)
        Text(text = randomUser.description, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun RandomUserProfileImage(imgUrl: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵 가져오기
    val bitmap : MutableState<Bitmap?> =  remember { mutableStateOf(null) }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>(){
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource // resource 는 다운 받은 이미지.
            }
        })

    bitmap.value?.asImageBitmap()?.let {
        Image(bitmap = it,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape)
        )
    } ?: Image(painter = painterResource(id = R.drawable.ic_profile_null),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeUIBasicTheme {
        ContextLayout()
    }
}
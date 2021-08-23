package com.example.composecookbookbasic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecookbookbasic.R
import com.example.composecookbookbasic.ui.carousel.CarouselLayout
import com.example.composecookbookbasic.ui.data.DemoDataProvider
import com.example.composecookbookbasic.ui.data.Item


class ListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListViewContent {
                onBackPressed()
            }
        }
    }

}

//상단 AppBar 및 Icon 생성
@Composable
fun ListViewContent(onBack: () -> Unit){
    // 상단 리스트 아이콘 클릭 시 값이 바뀜.
    val showListMenu = remember { mutableStateOf(false) }
    // 리스트 타입
    val listViewType = remember { mutableStateOf(ListViewType.VERTICAL.name)}
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)){
                        Text( text = "ListView", fontSize = 20.sp, fontWeight = FontWeight.Bold )
                        Text( text = listViewType.value, fontSize = 10.sp)
                    }
                },
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    //버튼 클릭 시 값 변경 true -> false/ false -> true
                    IconButton(onClick = { showListMenu.value = !showListMenu.value}){
                        Icon(Icons.Filled.Menu, null)
                    }
                }
            )
        }
    ) {
        ListScreen(showListMenu = showListMenu, listViewType = listViewType)
    }
}

// List 보여주는 부분
@Composable
fun ListScreen(showListMenu: MutableState<Boolean>, listViewType: MutableState<String>){
    //Data 가져옴
    val list = remember { DemoDataProvider.itemList }

    Box(modifier = Modifier.fillMaxSize()) {
        when(listViewType.value){
            ListViewType.VERTICAL.name -> VerticalListView(list)
            ListViewType.HORIZONTAL.name -> HorizontalListView(list)
            ListViewType.GRID.name -> GridListView(items = list)
            ListViewType.CAROUSEL.name -> CarouselLayout()
            else -> VerticalListView(list = list)
        }
        ListMenu(
            showListMenu = showListMenu,
            currentListViewType = listViewType,
            //align은 Box 안에 있을 때 만 사용 가능.
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
fun ListMenu(showListMenu: MutableState<Boolean>, currentListViewType: MutableState<String>, modifier: Modifier){
    Card(
        modifier = modifier
            .padding(10.dp)
            .animateContentSize()
            .width(200.dp)
        ,
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp),

        ){
        Column(
            horizontalAlignment = Alignment.Start
        ){
            // showListMenu 값이 true 일 때 ListMenu를 보여준다.
            if (showListMenu.value) {
                ListMenuItem(currentListViewType = currentListViewType, listType = ListViewType.VERTICAL.name, showListMenu = showListMenu)
                Divider(color = Color(0xffDDDDDD), thickness = 2.dp)
                ListMenuItem(currentListViewType = currentListViewType, listType = ListViewType.HORIZONTAL.name, showListMenu = showListMenu)
                Divider(color = Color(0xffDDDDDD), thickness = 2.dp)
                ListMenuItem(currentListViewType = currentListViewType, listType = ListViewType.GRID.name, showListMenu = showListMenu)
                Divider(color = Color(0xffDDDDDD), thickness = 2.dp)
                ListMenuItem(currentListViewType = currentListViewType, listType = ListViewType.CAROUSEL.name, showListMenu = showListMenu)
            }
        }

    }
}

// 리스트 메뉴의 옵션을 보여준다.
@Composable
fun ListMenuItem(currentListViewType: MutableState<String>, listType: String, showListMenu: MutableState<Boolean>){
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable(
                onClick = {
                    currentListViewType.value = listType
                    // 클릭하고 나면 리스트 메뉴 창 자동으로 닫힘
                    showListMenu.value = false
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        when (listType){
            ListViewType.VERTICAL.name -> {
                Icon(painter = painterResource(id = R.drawable.format_list_checkbox), null, modifier = Modifier.padding(10.dp))
                Text(text = ListViewType.VERTICAL.name, fontSize = 20.sp, fontWeight = FontWeight.Normal)
            }
            ListViewType.HORIZONTAL.name -> {
                Icon(painter = painterResource(id = R.drawable.format_list_text), null, modifier = Modifier.padding(10.dp))
                Text(text = ListViewType.HORIZONTAL.name, fontSize = 20.sp, fontWeight = FontWeight.Normal)
            }
            ListViewType.GRID.name -> {
                Icon(painter = painterResource(id = R.drawable.view_grid_outline), null, modifier = Modifier.padding(10.dp))
                Text(text = ListViewType.GRID.name, fontSize = 20.sp, fontWeight = FontWeight.Normal)
            }
            ListViewType.CAROUSEL.name -> {
                Icon(Icons.Filled.Swipe, null ,modifier = Modifier.padding(10.dp))
                Text(text = ListViewType.CAROUSEL.name, fontSize = 20.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}


@Composable
fun VerticalListView(list: List<Item>){
    LazyColumn {
        items(
            items = list,
            itemContent = {
                ListItemCardView(item = it)
            }
        )
    }
}

@Composable
fun HorizontalListView(list: List<Item>){
    LazyRow{
        items(
            items = list,
            itemContent = {
                ListItemCardView(item = it)
            }
        )
    }
}

@Composable 
fun GridListView(items: List<Item>){
    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        VerticalGrid(columns = 2){
            items.forEach{
                ListItemCardView(item = it)
            }
        }
    }
}


@Composable
fun ListItemCardView(item: Item){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
        content = {
            ListItemCardContext(item = item)
        }
    )
}

@Composable
fun ListItemCardContext(item: Item){
    Column {
        //image
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .height(180.dp), contentScale = ContentScale.Crop
            )
        //Title
        Text(text = item.title, fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(all = 5.dp))
        //drawline
        Divider(color = Color(0xffDDDDDD), thickness = 2.dp)
        //likeButton, view
        LikeIcon(item.likeCount)
    }
}

// 하트 아이콘
@Composable
fun LikeIcon(likeCount: Int){
    val isLike = remember { mutableStateOf(false)}
    val likeCount = remember{ mutableStateOf(likeCount) }
    Row(verticalAlignment = Alignment.CenterVertically){
        IconToggleButton(
            checked = isLike.value,
            onCheckedChange = {
                if (it) likeCount.value += 1 else likeCount.value -= 1
                isLike.value = !isLike.value
            },
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            if(isLike.value){
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            } else {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
        Text(text = likeCount.value.toString(), fontSize = 20.sp)
    }
}

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )
        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }
        // Track each columns height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
            .coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.place(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview2() {
    CarouselLayout()
}
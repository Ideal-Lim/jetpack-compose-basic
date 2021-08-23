package com.example.composecookbookbasic.ui.firstactivity

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecookbookbasic.ui.MainActivity
import com.example.composecookbookbasic.ui.VerticalGrid
import com.example.composecookbookbasic.ui.data.CategoryItem
import com.example.composecookbookbasic.ui.data.DemoDataProvider
import com.example.composecookbookbasic.ui.theme.uiextensions.generateDominantColorState
import com.example.composecookbookbasic.ui.theme.uiextensions.horizontalGradientBackground

@Composable
fun CategoryGrid(){
    val items = remember { DemoDataProvider.categoryItemList }

    VerticalGrid {
        items.forEach {
            CategoryGridItem(item = it)
        }
    }
}

@Composable
fun CategoryGridItem(item: CategoryItem) {
    val context = LocalContext.current
    //image resource 파일을 Bitmap 으로 반환
    val imageBitmap = ImageBitmap.imageResource(context.resources, item.imageId).asAndroidBitmap()
    val swatch = remember(item.id) { imageBitmap.generateDominantColorState() }
    val dominantGradient =
        remember { listOf(Color(swatch.rgb), Color(swatch.rgb).copy(alpha = 0.6f)) }

    val intent = Intent(context,MainActivity::class.java)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                //Disclaimer: We should pass event top level and there should startActivity
                context.startActivity(intent)
            })
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .horizontalGradientBackground(dominantGradient),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.category,
            style = typography.h6.copy(fontSize = 14.sp),
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .width(130.dp)
                .height(90.dp)
//                .align(Alignment.End)
                .graphicsLayer(translationX = 100f, rotationZ = 32f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CategoryGrid()
}
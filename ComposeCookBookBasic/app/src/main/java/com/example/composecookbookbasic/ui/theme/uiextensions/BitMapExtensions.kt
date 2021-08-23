package com.example.composecookbookbasic.ui.theme.uiextensions

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

// 대표 색 추출
fun Bitmap.generateDominantColorState(): Palette.Swatch = Palette.Builder(this)
    .resizeBitmapArea(0)
    .maximumColorCount(16)
    .generate()
    .swatches
    .maxByOrNull { swatch -> swatch.population }!!

package com.ideallim.mycomposelib.icon

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideallim.mycomposelib.R

@Composable
fun FaIcon(
    faIcon: FaIconType,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    tint: Color = Color.Unspecified
) {
    val scaleFactor = LocalConfiguration.current.fontScale

    val scaleIndependentFontSize =
        scaleIndependentFontSize(sizeInDp = size, scaleFactor = scaleFactor)

    val faTextStyle =
        TextStyle(
            color = tint,
            fontFamily = getFontFamily(faIcon),
            fontSize = scaleIndependentFontSize
        )

    BasicText(
        text = faIcon.src.codePointToString(),
        modifier = modifier,
        style = faTextStyle,
    )
}

private fun getFontFamily(faIconType: FaIconType): FontFamily {
    return when (faIconType) {
        is FaIconType.BrandIcon -> FontFamily(
            Font(resId = R.font.notosanskr_light)
        )
        is FaIconType.SolidIcon -> FontFamily(
            Font(resId = R.font.notosanskr_bold)
        )
        is FaIconType.RegularIcon -> FontFamily(
            Font(resId = R.font.notosanskr_medium)
        )
    }
}

/** @fun scaleIndependentFontSize: Since FA icons are font that requires Scale pixel
 * to render and will resize on device font settings. We want to keep icon size
 * constant to provided DP value so we calculate scale factor and cancel it if Any
 *
 * @materialIconOffset: FA icons at same dp taking more space then the Material Icon
 * Since we will be using both icons side by side this value offset Fa Icons to stay in line
 * with Material icons sizes.
 */

private fun scaleIndependentFontSize(sizeInDp: Dp, scaleFactor: Float): TextUnit {
    val materialIconOffset = 3.dp
    return ((sizeInDp - materialIconOffset).value  / scaleFactor).sp
}

private fun Int.codePointToString() = this.toChar().toString()

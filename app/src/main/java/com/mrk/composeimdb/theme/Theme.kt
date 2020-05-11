package com.mrk.composeimdb.theme

import androidx.compose.Composable
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.lightColorPalette
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.sp

val lightThemeColors = lightColorPalette(
    primary = Color(0xFF333333),
    primaryVariant = Color(0xFF333333),
    onPrimary = Color.White,
    secondary = Color(0xFFf5c518),
    onSecondary = Color(0xFFefe3a4),
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

val titleStyle = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

val subtitleStyle = TextStyle(
    fontWeight = FontWeight.Light,
    fontSize = 16.sp
)

@Preview
@Composable
fun displayFonts() {
    Box {
        Text(
            text = "titleStyle",
            style = titleStyle
        )
        Text(
            text = "subtitleStyle",
            style = subtitleStyle
        )
    }
}
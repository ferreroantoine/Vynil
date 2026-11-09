package com.example.myapplication.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyleTheme {

    val body = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

    val title = TextStyle(
        fontFamily = JacquardFontFamily,
        fontSize = 40.sp,
        color = ColorTheme.logoGreen
    )

    val homeTitle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 24.sp,
        letterSpacing = 1.5.sp
    )

    val cardTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
}

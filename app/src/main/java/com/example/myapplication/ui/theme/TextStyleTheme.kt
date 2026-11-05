package com.example.myapplication.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyleTheme {

    val body = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = ColorTheme.textPrimary
    )

    val title = TextStyle(
        fontFamily = JacquardFontFamily,
        fontSize = 40.sp,
        color = ColorTheme.logoGreen
    )
}

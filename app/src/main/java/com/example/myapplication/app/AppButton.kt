package com.example.myapplication.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

@Composable
fun AppButton(
    text: String,
    textFont: TextStyle = TextStyleTheme.body,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 2.dp,
    maxWidth: Boolean = true,
    padding: PaddingValues = PaddingValues(
        horizontal = Margin.l,
        vertical = Margin.m
    ),
    backgroundColor: Color = ColorTheme.backgroundPrimary,
    foregroundColor: Color = ColorTheme.textSecondary,
    onClick: () -> Unit
) {

    val widthModifier = if (maxWidth) Modifier.fillMaxWidth() else Modifier
    val buttonModifier = modifier
        .then(widthModifier)

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = shape,
        border = BorderStroke(borderWidth, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = foregroundColor,
        ),
        contentPadding = padding,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = textFont,
                color = foregroundColor,
            )
        }
    }
}

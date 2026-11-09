package com.example.myapplication.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

private const val VINYL_ROTATION_DURATION_MS = 4000
private const val VINYL_WIDTH_RATIO = 0.6f
private val CREATE_LOBBY_ICON_SIZE = 56.dp

@Composable
fun HomePage() {
    val vinylTransition = rememberInfiniteTransition(label = "vinylTransition")
    val vinylAngle by vinylTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = VINYL_ROTATION_DURATION_MS,
                easing = LinearEasing
            )
        ),
        label = "vinylAngle"
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Margin.l),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_profile),
            style = TextStyleTheme.cardTitle,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Margin.l)
        )
        Image(
            painter = painterResource(R.drawable.vinyl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(VINYL_WIDTH_RATIO)
                .rotate(vinylAngle)
        )
        Text(
            text = stringResource(R.string.home_title),
            style = TextStyleTheme.homeTitle,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Margin.xl)
        )
        CreateLobbyButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Margin.xl)
        )
    }
}

@Composable
private fun CreateLobbyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(CREATE_LOBBY_ICON_SIZE)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(ColorTheme.lightGreen, ColorTheme.logoGreen)
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Text(
            text = stringResource(R.string.home_create_lobby),
            style = TextStyleTheme.cardTitle,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(start = Margin.l)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
    HomePage()
}



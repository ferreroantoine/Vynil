package com.example.myapplication.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.app.ShrinkOnPressBox
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

private const val VINYL_ROTATION_DURATION_MS = 4000
private const val VINYL_WIDTH_RATIO = 0.6f

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
            .padding(horizontal = Margin.xxxl, vertical = Margin.l),
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
        ShrinkOnPressBox(
            onClick = {},
            modifier = Modifier.fillMaxWidth(VINYL_WIDTH_RATIO)
        ) {
            Image(
                painter = painterResource(R.drawable.vinyl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(vinylAngle)
            )
        }
        Text(
            text = stringResource(R.string.home_title),
            style = TextStyleTheme.homeTitle,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Margin.xl)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
    HomePage()
}



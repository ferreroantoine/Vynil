package com.example.myapplication.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.app.ShrinkOnPressBox
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

private const val VINYL_ROTATION_DURATION_MS = 4000
private const val VINYL_WIDTH_RATIO = 0.6f
private val PROFILE_AVATAR_SIZE = 44.dp

@Composable
fun HomePage(
    username: String = "chandrama",
    onProfileClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {}
) {
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
        HomeTopBar(
            username = username,
            onProfileClick = onProfileClick,
            onNotificationsClick = onNotificationsClick
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

@Composable
private fun HomeTopBar(
    username: String,
    onProfileClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Margin.l),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onProfileClick)
        ) {
            Image(
                painter = painterResource(R.drawable.pp_example_background),
                contentDescription = stringResource(R.string.home_profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(PROFILE_AVATAR_SIZE)
                    .clip(CircleShape)
                    .border(2.dp, ColorTheme.logoGreen, CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = Margin.m)
            ) {
                Text(
                    text = stringResource(R.string.home_welcome),
                    style = TextStyleTheme.cardTitle.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = username,
                    style = TextStyleTheme.body.copy(
                        fontSize = 13.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }

        IconButton(onClick = onNotificationsClick) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.home_notifications),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
    HomePage()
}

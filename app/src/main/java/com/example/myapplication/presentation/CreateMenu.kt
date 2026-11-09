package com.example.myapplication.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.app.ShrinkOnPressBox
import com.example.myapplication.ui.theme.AppRadius
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

private const val SCRIM_ALPHA = 0.7f
private val OPTION_ICON_SIZE = 56.dp

@Composable
fun CreateMenu(
    bottomPadding: Dp,
    onDismiss: () -> Unit,
    onCreateBlindTest: () -> Unit,
    onCreateLobby: () -> Unit
) {
    BackHandler(onBack = onDismiss)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = SCRIM_ALPHA))
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = bottomPadding)
                .padding(Margin.m)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(AppRadius.xxl)
                )
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = {}
                )
                .padding(Margin.l),
            verticalArrangement = Arrangement.spacedBy(Margin.l)
        ) {
            CreateMenuOption(
                icon = Icons.Outlined.MusicNote,
                title = stringResource(R.string.create_menu_blind_test_title),
                description = stringResource(R.string.create_menu_blind_test_description),
                onClick = onCreateBlindTest
            )
            CreateMenuOption(
                icon = Icons.Outlined.Groups,
                title = stringResource(R.string.create_menu_lobby_title),
                description = stringResource(R.string.create_menu_lobby_description),
                onClick = onCreateLobby
            )
        }
    }
}

@Composable
private fun CreateMenuOption(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    ShrinkOnPressBox(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(OPTION_ICON_SIZE)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Column(modifier = Modifier.padding(start = Margin.l)) {
                Text(
                    text = title,
                    style = TextStyleTheme.cardTitle,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = TextStyleTheme.body,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateMenuPreview() {
    CreateMenu(
        bottomPadding = 0.dp,
        onDismiss = {},
        onCreateBlindTest = {},
        onCreateLobby = {}
    )
}

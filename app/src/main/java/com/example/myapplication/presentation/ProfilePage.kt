package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.AppRadius
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

data class RecentBlindtest(
    val id: String,
    val title: String,
    val score: String,
    val date: String,
    val coverRes: Int = R.drawable.vinyl,
)

data class CreatedBlindtest(
    val id: String,
    val title: String,
    val trackCount: Int,
    val coverRes: Int = R.drawable.pp_example_background,
)

private val sampleRecentBlindtests = listOf(
    RecentBlindtest("1", "K-Pop Top Hits", "9/10", "Hier", R.drawable.pp_example_background),
    RecentBlindtest("2", "Années 2000", "8/10", "Il y a 3 jours", R.drawable.vinyl),
    RecentBlindtest("3", "Rock & Metal", "10/10", "Il y a 5 jours", R.drawable.logo),
)

private val sampleCreatedBlindtests = listOf(
    CreatedBlindtest("1", "Mes Pépites Hip-Hop", 15, R.drawable.vinyl),
    CreatedBlindtest("2", "Blindtest French Touch", 20, R.drawable.pp_example_background),
    CreatedBlindtest("3", "OST Animes Cultes", 12, R.drawable.logo),
)

@Composable
fun ProfilePage(
    username: String = "chandrama",
    recentBlindtests: List<RecentBlindtest> = sampleRecentBlindtests,
    createdBlindtests: List<CreatedBlindtest> = sampleCreatedBlindtests,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Margin.l),
        verticalArrangement = Arrangement.spacedBy(Margin.l),
        contentPadding = PaddingValues(top = Margin.l, bottom = Margin.xxl)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.pp_example_background),
                    contentDescription = stringResource(R.string.home_profile),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(Margin.s))

                Text(
                    text = username,
                    style = TextStyleTheme.cardTitle.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.profile_recent_blindtests),
                    style = TextStyleTheme.cardTitle.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = Margin.m)
                )

                for (item in recentBlindtests) {
                    RecentBlindtestRow(item = item)
                    Spacer(modifier = Modifier.height(Margin.s))
                }
            }
        }

        item {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.profile_created_blindtests),
                    style = TextStyleTheme.cardTitle.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = Margin.m)
                )

                for (item in createdBlindtests) {
                    CreatedBlindtestRow(item = item)
                    Spacer(modifier = Modifier.height(Margin.s))
                }
            }
        }
    }
}

@Composable
private fun RecentBlindtestRow(item: RecentBlindtest) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margin.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(item.coverRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(AppRadius.m))
            )

            Spacer(modifier = Modifier.width(Margin.m))

            Column {
                Text(
                    text = item.title,
                    style = TextStyleTheme.body.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = item.date,
                    style = TextStyleTheme.body.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )
            }
        }

        Text(
            text = item.score,
            style = TextStyleTheme.body.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = ColorTheme.logoGreen
            )
        )
    }
}

@Composable
private fun CreatedBlindtestRow(item: CreatedBlindtest) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margin.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.coverRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(AppRadius.m))
        )

        Spacer(modifier = Modifier.width(Margin.m))

        Column {
            Text(
                text = item.title,
                style = TextStyleTheme.body.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${item.trackCount} titres",
                style = TextStyleTheme.body.copy(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            )
        }
    }
}

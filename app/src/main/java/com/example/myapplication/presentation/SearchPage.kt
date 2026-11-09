package com.example.myapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.Track
import com.example.myapplication.data.repository.TrackRepository
import com.example.myapplication.data.repository.TrackRepositoryImpl
import com.example.myapplication.datasource.api.Resource
import com.example.myapplication.ui.theme.AppRadius
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme
import kotlinx.coroutines.launch

private data class CategoryItem(
    val title: String,
    val color: Color
)

private val topGenres = listOf(
    CategoryItem("Kpop", Color(0xFF59B335)),
    CategoryItem("Indie", Color(0xFFD81B60)),
    CategoryItem("R&B", Color(0xFF455A64)),
    CategoryItem("Pop", Color(0xFFD84315))
)

private val browseAll = listOf(
    CategoryItem("Fait pour toi", Color(0xFF1D82A4)),
    CategoryItem("SORTIE", Color(0xFF7B1FA2)),
    CategoryItem("Charts", Color(0xFF1565C0)),
    CategoryItem("IA", Color(0xFFB71C1C)),
    CategoryItem("Bollywood", Color(0xFF4E342E)),
    CategoryItem("Pop Fusion", Color(0xFF004D40))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    bottomPadding: Dp,
    trackRepository: TrackRepository = TrackRepositoryImpl()
) {
    var query by rememberSaveable { mutableStateOf("") }
    var result by remember { mutableStateOf<Resource<List<Track>>?>(null) }
    val scope = rememberCoroutineScope()

    val searchCategory: (String) -> Unit = { categoryName ->
        query = categoryName
        scope.launch {
            result = try {
                Resource.Success(trackRepository.searchTracks(categoryName))
            } catch (e: Exception) {
                Resource.Error("Erreur de chargement : ${e.message}")
            }
        }
    }

    Column(modifier = Modifier.padding(Margin.l)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Margin.s)
        ) {
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(48.dp),
                tint = ColorTheme.logoGreen
            )
            Text(
                text = "Search",
                style = TextStyleTheme.title
            )
        }

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Margin.s),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        query = it
                        if (it.isBlank()) {
                            result = null
                        }
                    },
                    onSearch = {
                        scope.launch {
                            result = try {
                                Resource.Success(trackRepository.searchTracks(it))
                            } catch (e: Exception) {
                                Resource.Error("Erreur de chargement : ${e.message}")
                            }
                        }
                    },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = { Text("Artistes, musiques...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Rechercher") }
                )
            },
            expanded = false,
            onExpandedChange = { }
        ) { }

        if (query.isNotBlank() || result != null) {
            when (val currentResult = result) {
                is Resource.Success -> LazyColumn(
                    modifier = Modifier.padding(top = Margin.s),
                    contentPadding = PaddingValues(bottom = bottomPadding)
                ) {
                    items(currentResult.data, key = { it.id }) { track ->
                        TrackItem(track)
                    }
                }

                is Resource.Error -> Text(
                    text = currentResult.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = Margin.s)
                )

                null -> {}
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = bottomPadding),
                verticalArrangement = Arrangement.spacedBy(Margin.m)
            ) {
                item {
                    Text(
                        text = "Ton Top Genres",
                        style = TextStyleTheme.cardTitle,
                        modifier = Modifier.padding(top = Margin.s, bottom = Margin.xs)
                    )
                }

                items(topGenres.chunked(2)) { rowCategories ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Margin.m)
                    ) {
                        for (category in rowCategories) {
                            CategoryCard(
                                category = category,
                                modifier = Modifier.weight(1f),
                                onCategoryClick = { searchCategory(it) }
                            )
                        }
                        if (rowCategories.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                item {
                    Text(
                        text = "Tout parcourir",
                        style = TextStyleTheme.cardTitle,
                        modifier = Modifier.padding(top = Margin.l, bottom = Margin.xs)
                    )
                }

                items(browseAll.chunked(2)) { rowCategories ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Margin.m)
                    ) {
                        for (category in rowCategories) {
                            CategoryCard(
                                category = category,
                                modifier = Modifier.weight(1f),
                                onCategoryClick = { searchCategory(it) }
                            )
                        }
                        if (rowCategories.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: CategoryItem,
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(96.dp)
            .clickable { onCategoryClick(category.title) },
        shape = RoundedCornerShape(AppRadius.l),
        colors = CardDefaults.cardColors(containerColor = category.color)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
        ) {
            Text(
                text = category.title,
                style = TextStyleTheme.body.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(Margin.m)
                    .align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 12.dp, y = 10.dp)
                    .rotate(25f)
                    .size(60.dp)
                    .clip(RoundedCornerShape(AppRadius.m))
                    .background(Color.White.copy(alpha = 0.25f))
            ) {
                Icon(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Margin.s),
                    tint = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun TrackItem(track: Track) {
    ListItem(
        headlineContent = { Text(track.title) },
        supportingContent = { Text("${track.artist?.name} • ${track.album?.title}") }
    )
}

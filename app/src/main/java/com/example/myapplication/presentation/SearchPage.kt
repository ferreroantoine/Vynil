package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.Track
import com.example.myapplication.data.repository.TrackRepository
import com.example.myapplication.data.repository.TrackRepositoryImpl
import com.example.myapplication.datasource.api.Resource
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(trackRepository: TrackRepository = TrackRepositoryImpl()) {
    var query by rememberSaveable { mutableStateOf("") }
    var result by remember { mutableStateOf<Resource<List<Track>>?>(null) }
    val scope = rememberCoroutineScope()

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
            modifier = Modifier.fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
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

        when (val currentResult = result) {
            is Resource.Success -> LazyColumn(modifier = Modifier.padding(top = Margin.s)) {
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
    }
}

@Composable
fun TrackItem(track: Track) {
    ListItem(
        headlineContent = { Text(track.title) },
        supportingContent = { Text("${track.artist?.name} • ${track.album?.title}") }
    )
}

package com.example.myapplication.presentation

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.JacquardFontFamily
import com.example.myapplication.ui.theme.Logo_green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage() {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val searchResults = remember(query) {
        if (query.isEmpty()) emptyList()
        else listOf("Résultat 1", "Résultat 2", "Résultat 3").filter {
            it.contains(query, ignoreCase = true)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(48.dp),
                tint = Logo_green
            )
            Text(
                text = "Search",
                fontFamily = JacquardFontFamily,
                color = Logo_green,
                fontSize = 40.sp
            )
        }
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { expanded = false },
                    expanded = false,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Recherche...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Artistes, musiques"
                        )
                    }
                )
            },
            expanded = false,
            onExpandedChange = { expanded = it }
        ) {


            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                query = result
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }



        }
    }
}
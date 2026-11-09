package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@Composable
fun LibraryPage() {
    Column {
        Text(text = stringResource(R.string.nav_library))
    }
}

package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.app.AppButton
import com.example.myapplication.data.repository.TrackRepository
import com.example.myapplication.data.repository.TrackRepositoryImpl
import com.example.myapplication.ui.theme.Margin
import com.example.myapplication.ui.theme.TextStyleTheme

@Composable
fun HomePage(trackRepository: TrackRepository = TrackRepositoryImpl()) {

    LaunchedEffect(true) {
        val response = trackRepository.getTracks()
        println(response)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Margin.l),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vynil",
            style = TextStyleTheme.title
        )

        AppButton(
            text = "Lancer un blind test",
            maxWidth = true,
        ) {

        }
    }
}

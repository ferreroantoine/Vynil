package com.example.myapplication.data

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeKey : NavKey

@Serializable
data object Home : HomeKey

@Serializable
data class HomeDetail(val id: String) : HomeKey

@Serializable
data object Search : NavKey

@Serializable
data object Profile : NavKey

@Serializable
data object Leaderboard : NavKey

enum class MainTab { HOME, SEARCH, PROFILE, LEADERBOARD }

// AppButton
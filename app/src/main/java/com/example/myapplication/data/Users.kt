package com.example.myapplication.data

import android.media.Image
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val username: String,
    val password: String? = null,
    val email: String? = null,
    val imgProfile: ImageBitmap,
)
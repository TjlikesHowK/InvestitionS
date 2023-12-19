package com.example.investitions.adapters

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarElements (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Portfolio: BottomBarElements(
        route = "portfolio",
        title = "Portfolio",
        icon = Icons.Default.AccountCircle
    )

    object Search: BottomBarElements(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Analitics: BottomBarElements(
        route = "analitics",
        title = "Analitics",
        icon = Icons.Default.Share
    )
}
package com.example.investitions.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.investitions.adapters.MainViewModel
import com.example.investitions.ui_elements.SearchElement

@Composable
fun SearchScreen(mainViewModel: MainViewModel, context: Context){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        SearchElement(
            mainViewModel = mainViewModel, context = context
        )
    }
}
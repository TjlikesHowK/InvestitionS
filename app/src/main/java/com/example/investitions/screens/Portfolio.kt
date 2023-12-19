package com.example.investitions.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.investitions.adapters.SearchStockData
import com.example.investitions.ui_elements.CustomItemDel

var ALLDATA_portfolio = listOf<SearchStockData>().toMutableList()

@Composable
fun PortfolioScreen(){
    val getData = rememberSaveable{listOf<SearchStockData>()}.toMutableList()
    getData.addAll(ALLDATA_portfolio)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        LazyColumn(
            contentPadding = PaddingValues(all = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(items = getData){ data ->
                CustomItemDel(data = data)
            }
        }
    }
}
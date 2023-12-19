package com.example.investitions.screens

import android.content.Context
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
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.investitions.adapters.DBHandler
import com.example.investitions.adapters.SearchStockData
import com.example.investitions.ui_elements.CustomItemDel

var ALLDATA_portfolio = listOf<SearchStockData>().toMutableList()

@Composable
fun PortfolioScreen(context: Context){
    val getData = rememberSaveable{listOf<SearchStockData>()}.toMutableList()

    if(ALLDATA_portfolio.isNotEmpty()){
        getData.addAll(ALLDATA_portfolio)
    }else{
        val dbHandler = DBHandler(context);
        getData.addAll(dbHandler.read())
        ALLDATA_portfolio.addAll(dbHandler.read())
    }

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
                CustomItemDel(data = data, context = context)
            }
        }
    }
}
package com.example.investitions.ui_elements

import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investitions.adapters.SearchStockData
import com.example.investitions.screens.ALLDATA_portfolio

@Composable
fun CustomItemDel(data: SearchStockData){
    Row (
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(25.dp))
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(25.dp))
            .padding(7.dp)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Image(
            painter = rememberAsyncImagePainter(data.logo),
            contentDescription = null,
            modifier = Modifier
                .height(55.dp)
                .width(55.dp)
        )
        Column (
            modifier = Modifier
                .weight(1.0f, true),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                modifier = Modifier.padding(bottom = 5.dp, start = 2.dp),
                text = data.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(start = 1.dp),
                text = data.ticker,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
        }
        Column(
            modifier = Modifier
                .weight(1.0f, true)
                .padding(end = 10.dp),
            horizontalAlignment = Alignment.End
        ) {
            IconButton(
                onClick = { ALLDATA_portfolio.removeAt(ALLDATA_portfolio.indexOf(SearchStockData(data.logo, data.name, data.ticker)))}
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun preview(){
    CustomItemDel(
        data = SearchStockData(
        logo = "https://static.finnhub.io/logo/87cb30d8-80df-11ea-8951-00000000092a.png",
        name = "STKull",
        ticker = "STK")
    )
}
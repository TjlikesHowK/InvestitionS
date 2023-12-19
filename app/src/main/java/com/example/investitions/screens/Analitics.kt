package com.example.investitions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investitions.adapters.getCompanyData
import com.example.investitions.ui_elements.PieChart
import kotlinx.serialization.json.JsonObject

@Composable
fun AnaliticsScreen(){
    val industryType = mutableListOf<String>()
    val currencyType = mutableListOf<String>()
    val countryType = mutableListOf<String>()
    var data: JsonObject
    var industry: String
    var currency: String
    var country: String
    val data_1 = mutableMapOf<String, Int>()
    val data_2 = mutableMapOf<String, Int>()
    val data_3 = mutableMapOf<String, Int>()

    for (i in 0 until ALLDATA_portfolio.size){
        data = getCompanyData(ALLDATA_portfolio.toList()[i].ticker.replace("\"", ""))

        industry = try {
            data.getValue("finnhubIndustry").toString().replace("\"", "")
        }catch (_: Exception){
            "unknown"
        }
        currency = try {
            data.getValue("currency").toString().replace("\"", "")
        }catch (_: Exception){
            "unknown"
        }
        country = try {
            data.getValue("country").toString().replace("\"", "")
        }catch (_: Exception){
            "unknown"
        }

        industryType += industry
        currencyType += currency
        countryType += country
    }

    for (i in industryType.groupingBy { it }.eachCount().filter { it.value >= 1 }.keys){
        data_1 += Pair(i, industryType.count { it == i })
    }
    for (i in currencyType.groupingBy { it }.eachCount().filter { it.value >= 1 }.keys){
        data_2 += Pair(i, currencyType.count { it == i })
    }
    for (i in countryType.groupingBy { it }.eachCount().filter { it.value >= 1 }.keys){
        data_3 += Pair(i, countryType.count { it == i })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9D9D9)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (modifier = Modifier.verticalScroll(rememberScrollState())){
            Row {

                Box(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
                        .fillMaxHeight()
                        .background(Color(0xFF811FFF))
                ) {
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Industry data:",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    PieChart(
                        data = data_1,
                        paddingTop = 55
                    )
                }
            }
            Row {
                Box(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
                        .fillMaxHeight()
                        .background(Color(0xFF811FFF))
                ) {
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Currency data:",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    PieChart(
                        data = data_2,
                        paddingTop = 55
                    )
                }
            }
            Row {
                Box(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
                        .height(LocalConfiguration.current.screenHeightDp.dp)
                        .background(Color(0xFF811FFF))
                ) {
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Country data:",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    PieChart(
                        data = data_3,
                        paddingTop = 55
                    )
                }
            }
        }
    }
}
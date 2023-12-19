package com.example.investitions.adapters

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.investitions.screens.AnaliticsScreen
import com.example.investitions.screens.PortfolioScreen
import com.example.investitions.screens.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController, context: Context){
    NavHost(
        navController = navController,
        startDestination = BottomBarElements.Portfolio.route)
    {
        composable(
            route = BottomBarElements.Portfolio.route)
        {
            PortfolioScreen(context = context)
        }

        composable(route = BottomBarElements.Search.route)
        {
            SearchScreen(mainViewModel = MainViewModel(), context = context)
        }

        composable(route = BottomBarElements.Analitics.route)
        {
            AnaliticsScreen()
        }
    }
}
package com.example.investitions.adapters

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.investitions.screens.AnaliticsScreen
import com.example.investitions.screens.PortfolioScreen
import com.example.investitions.screens.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarElements.Portfolio.route)
    {
        composable(
            route = BottomBarElements.Portfolio.route)
        {
            PortfolioScreen()
        }

        composable(route = BottomBarElements.Search.route)
        {
            SearchScreen(mainViewModel = MainViewModel())
        }

        composable(route = BottomBarElements.Analitics.route)
        {
            AnaliticsScreen()
        }
    }
}
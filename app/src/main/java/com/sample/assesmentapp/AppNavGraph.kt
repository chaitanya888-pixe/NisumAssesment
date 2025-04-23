package com.sample.assesmentapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.core.utils.NavRoute
import com.sample.presentation.ui.DetailScreen
import com.sample.presentation.ui.MainScreen
import com.sample.presentation.viewmodel.UsersViewModel

@Composable
fun AppNavGraph( viewModel: UsersViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavRoute.List.route) {
        composable(NavRoute.List.route) { MainScreen(viewModel, navController) }
        composable(
            route = NavRoute.Details.route) { backStackEntry ->
            DetailScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}
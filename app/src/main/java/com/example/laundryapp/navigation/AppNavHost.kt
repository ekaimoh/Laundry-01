package com.example.laundryapp.navigation

import android.view.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.Key.Companion.Menu
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laundryapp.ui.theme.Screens.Client.AddClient
import com.example.laundryapp.ui.theme.Screens.Client.UpdateClient
import com.example.laundryapp.ui.theme.Screens.Client.ViewClientsScreen
import com.example.laundryapp.ui.theme.Screens.Confirm.Login
import com.example.laundryapp.ui.theme.Screens.Details.Greeting
import com.example.morningmobileappmvvm.ui.theme.screens.clients.Menu

import com.example.morningmobileappmvvm.ui.theme.screens.home.HomeScreens

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_MENU
) {


    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_REGISTER) { Greeting(name = "LOCAL JOBS OPPORTUNITIES", navController) }
        composable(ROUTE_HOME) { HomeScreens(navController) }
        composable(ROUTE_MENU){ Menu(navController) }
        composable(ROUTE_LOGIN) { Login(navController) }
        composable(ROUTE_ADD_CLIENT) { AddClient(navController) }
        composable(ROUTE_VIEW_CLIENT) { ViewClientsScreen(navController) }
        composable(ROUTE_UPDATE_CLIENT + "/{id}") { passedData ->
            UpdateClient(
                navController, passedData.arguments?.getString("id")!!
            )
        }
    }
}






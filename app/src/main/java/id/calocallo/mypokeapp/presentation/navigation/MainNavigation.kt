package id.calocallo.mypokeapp.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.calocallo.mypokeapp.presentation.home.HomeScreen
import id.calocallo.mypokeapp.presentation.home.ProfileScreen

@Composable
fun MainNavigation(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()


    // Bottom Navigation Bar
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = navController.currentDestination?.route == "home",
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = navController.currentDestination?.route == "profile",
                    onClick = {
                        navController.navigate("profile")
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(
                        onPokemonClick = { pokemonName ->
                            navController.navigate("pokemon_detail/$pokemonName")
                        }
                    )
                }

                composable("profile") {
                    ProfileScreen(onLogout = onLogout)
                }

                composable(
                    "pokemon_detail/{pokemonName}",
                    arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                    // You can create a PokemonDetailScreen composable to display the details
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Pokemon Detail Screen for $pokemonName")
                    }
                }
            }
        }
    }
}
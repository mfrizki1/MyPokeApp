package id.calocallo.mypokeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.calocallo.mypokeapp.presentation.auth.LoginScreen
import id.calocallo.mypokeapp.presentation.auth.RegisterScreen

@Composable
fun AuthNavigation(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = onAuthSuccess,
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = onAuthSuccess,
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
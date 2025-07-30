package id.calocallo.mypokeapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import id.calocallo.mypokeapp.presentation.auth.AuthViewModel
import id.calocallo.mypokeapp.presentation.navigation.AuthNavigation
import id.calocallo.mypokeapp.presentation.navigation.MainNavigation

@Composable
fun MyPokeApp() {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val authNavController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isLoggedIn) {
            MainNavigation(onLogout = { authViewModel.logout() })
        } else {
            AuthNavigation(
                navController = authNavController,
                onAuthSuccess = {
                    authViewModel.setLoggedIn()
                }
            )
        }
    }
}
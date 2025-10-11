package life.munay.geojson.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import life.munay.about.screens.AboutMunay
import life.munay.about.viewmodels.AboutViewModel
import life.munay.core.navigation.NavigationRoutes
import life.munay.geojson.viewmodels.MainViewModel
import life.munay.maps.screens.GeoJsonMap

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRoutes.Root.route,
    statusBarUpdates: (Color, Boolean) -> Unit
) {
    val errorMessage = remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.Root.route) {
            navController.navigate(NavigationRoutes.Maps.route) {
                popUpTo(NavigationRoutes.Root.route) { inclusive = true }
            }
        }
        composable(route = NavigationRoutes.Maps.route) {
            GeoJsonMap()
        }
        composable(route = NavigationRoutes.About.route) {
            val aboutViewModel: AboutViewModel = hiltViewModel()

            AboutMunay(
                aboutViewModel = aboutViewModel
            )
        }
    }

    if (errorMessage.value.isNotEmpty()) {
//        ErrorMessageView(
//            message = errorMessage.value,
//            onDismiss = { errorMessage.value = "" }
//        )
    }
}

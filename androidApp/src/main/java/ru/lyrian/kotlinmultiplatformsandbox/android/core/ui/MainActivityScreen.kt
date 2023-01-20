package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.bottomNavigation.BottomNavigationBar
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.navigation.NavigationRoot

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavigationRoot(navController = navController)
    }
}

package ru.lyrian.kotlinmultiplatformsandbox.android.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.theme.MyApplicationTheme
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui.Launches

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Launches()
            }
        }
    }
}

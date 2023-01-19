package ru.lyrian.kotlinmultiplatformsandbox.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui.SpaceXContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                SpaceXContent()
            }
        }
    }
}

package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.showShortToast
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                SpaceXContent()
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.event.collect {
                    when(it) {
                        is MainActivityEvent.ShowToast -> { showShortToast(it.message) }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.refresh()
    }
}

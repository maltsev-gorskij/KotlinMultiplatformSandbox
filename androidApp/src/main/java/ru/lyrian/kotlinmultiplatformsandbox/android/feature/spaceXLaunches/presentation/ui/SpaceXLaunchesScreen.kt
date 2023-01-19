package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.model.SpaceXLaunchesState
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.viewmodel.SpaceXLaunchesViewModel

@Composable
fun SpaceXContent(viewModel: SpaceXLaunchesViewModel = koinViewModel()) {
    val currentViewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.refresh()
        viewModel.event.collect {
            when (it) {
                is SpaceXLaunchesEvent.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SpaceXHeader()
            SpaceXLaunches(
                viewState = currentViewState,
                onRefresh = { viewModel.refresh(true) }
            )
        }
    }
}

@Composable
private fun SpaceXHeader() {
    Box(
        Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primary
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp),
                text = "SpaceX Launches",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun SpaceXLaunches(
    viewState: SpaceXLaunchesState,
    onRefresh: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewState.isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true
            )
        }
    ) {
        SpaceXLaunchesList(viewState)
    }
}

@Composable
private fun SpaceXLaunchesList(viewState: SpaceXLaunchesState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (viewState.isError) {
            item() {
                Box(modifier = Modifier.fillParentMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = viewState.errorMessage,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        } else {
            items(items = viewState.launches) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Launch name: ${it.missionName}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = if (it.launchSuccess == true) "Successful" else "Unsuccessful",
                            style = MaterialTheme.typography.caption.copy(
                                color = if (it.launchSuccess == false) {
                                    MaterialTheme.colors.error
                                } else {
                                    LocalTextStyle.current.color
                                }
                            )
                        )
                        Text(
                            text = "Launch year: ${it.launchYear}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Launch details: ${it.details ?: ""}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}

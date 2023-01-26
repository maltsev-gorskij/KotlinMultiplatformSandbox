package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.model.LaunchesListState
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.viewmodel.LaunchesListViewModel
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

@Composable
fun LaunchesListScreen(
    onLaunchClicked: (String) -> Unit
) {
    val viewModel = getViewModel<LaunchesListViewModel>()
    val currentViewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.event.collect {
            when (it) {
                is LaunchesListEvent.ShowToast -> {
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
            LaunchesHeader()
            LaunchesList(
                viewState = currentViewState,
                onRefresh = { viewModel.refresh(true) },
                onLaunchClicked = onLaunchClicked
            )
        }
    }
}

@Composable
private fun LaunchesHeader() {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LaunchesList(
    viewState: LaunchesListState,
    onRefresh: () -> Unit,
    onLaunchClicked: (String) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewState.isLoading,
        onRefresh = onRefresh
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LaunchesListContent(
            viewState = viewState,
            onLaunchClicked = onLaunchClicked
        )
        PullRefreshIndicator(
            refreshing = viewState.isLoading,
            state = pullRefreshState,
            scale = true,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun LaunchesListContent(
    viewState: LaunchesListState,
    onLaunchClicked: (String) -> Unit
) {
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
                LaunchesListItem(
                    rocketLaunch = it,
                    onLaunchClicked = { onLaunchClicked(it.id) }
                )
            }
        }
    }
}

@Composable
private fun LaunchesListItem(
    rocketLaunch: RocketLaunch,
    onLaunchClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLaunchClicked() },
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Launch name: ${rocketLaunch.missionName}",
                style = MaterialTheme.typography.caption
            )
            Text(
                text = if (rocketLaunch.launchSuccess == true) "Successful" else "Unsuccessful",
                style = MaterialTheme.typography.caption.copy(
                    color = if (rocketLaunch.launchSuccess == false) {
                        MaterialTheme.colors.error
                    } else {
                        LocalTextStyle.current.color
                    }
                )
            )
            Text(
                text = "Launch year: ${rocketLaunch.launchYear}",
                style = MaterialTheme.typography.caption
            )
            Text(
                text = "Launch details: ${rocketLaunch.details ?: ""}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.getViewModel
import ru.lyrian.kotlinmultiplatformsandbox.android.core.ui.composables.images.ImageFromUrl
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model.LaunchDetailsEvent
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model.LaunchDetailsState
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.viewmodel.LaunchDetailsViewModel
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

@Composable
fun LaunchDetailsScreen(
    onNavigateBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = getViewModel<LaunchDetailsViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.eventChannel.collect { event ->
            when (event) {
                is LaunchDetailsEvent.ShowErrorMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            LaunchDetailsTopBar(
                title = state.launch?.missionName,
                onNavigateBackClicked = onNavigateBackClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LaunchDetailsContent(
            state = state,
            onRefresh = viewModel::refreshLaunchDetails,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun LaunchDetailsTopBar(
    title: String?,
    onNavigateBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = title.orEmpty(), style = MaterialTheme.typography.h6)
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LaunchDetailsContent(
    state: LaunchDetailsState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = onRefresh,
    )

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState),
    ) {
        when {
            state.isError -> {
                Text(
                    text = "Oops...",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.launch != null -> {
                LaunchDetails(
                    rocketLaunch = state.launch,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            scale = true,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun LaunchDetails(
    rocketLaunch: RocketLaunch,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (rocketLaunch.flickrImagesUrls.isNotEmpty() || !rocketLaunch.patchImageUrl.isNullOrEmpty()) {
            LaunchImages(
                flickrImagesUrls = rocketLaunch.flickrImagesUrls,
                patchImageUrl = rocketLaunch.patchImageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }

        LaunchDetailsTitleAndText(
            title = "Start Date",
            text = rocketLaunch.launchDateUTC,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        LaunchDetailsSuccess(
            success = rocketLaunch.launchSuccess,
            failureReasons = rocketLaunch.failureReasons,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        rocketLaunch.details?.let { details ->
            LaunchDetailsTitleAndText(
                title = "Launch details",
                text = details,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun LaunchImages(
    flickrImagesUrls: List<String>,
    patchImageUrl: String?,
    modifier: Modifier = Modifier
) {
    if (flickrImagesUrls.isNotEmpty()) {
        val pagerState = rememberPagerState()
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                count = flickrImagesUrls.size,
            ) {
                ImageFromUrl(
                    model = flickrImagesUrls[it],
                    contentDescription = "Flicker Image $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
    } else if (!patchImageUrl.isNullOrEmpty()) {
        ImageFromUrl(
            model = patchImageUrl,
            contentDescription = "Patch image",
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(2f)
        )
    }
}

@Composable
private fun LaunchDetailsSuccess(
    success: Boolean?,
    failureReasons: List<String>,
    modifier: Modifier = Modifier
) {
    val (successText, successColor) = if (success == true) {
        "Successful" to LocalTextStyle.current.color
    } else {
        "Unsuccessful" to MaterialTheme.colors.error
    }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = successText,
            style = MaterialTheme.typography.h6,
            color = successColor
        )
        if (success != true && failureReasons.isNotEmpty()) {
            Text(text = "Failure reasons", style = MaterialTheme.typography.h6)
            failureReasons.forEach {  reason ->
                Text(text = reason, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
private fun LaunchDetailsTitleAndText(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}
